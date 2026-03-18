package com.updapm.privacy.presentation.permissions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.updapm.privacy.data.local.entity.AppPermission
import com.updapm.privacy.data.repository.LogRepository
import com.updapm.privacy.data.repository.PermissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PermissionUiState(
    val permissions: List<AppPermission> = emptyList(),
    val groupedByApp: Map<String, List<AppPermission>> = emptyMap(),
    val isLoading: Boolean = true
)

@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val permissionRepository: PermissionRepository,
    private val logRepository: LogRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(PermissionUiState())
    val uiState: StateFlow<PermissionUiState> = _uiState.asStateFlow()
    
    init {
        syncRealPermissions()
        loadPermissions()
    }
    
    private fun syncRealPermissions() {
        viewModelScope.launch {
            try {
                permissionRepository.syncRealPermissions()
            } catch (e: Exception) {
                // Handle error silently
            }
        }
    }
    
    private fun loadPermissions() {
        viewModelScope.launch {
            permissionRepository.getAllPermissions()
                .collect { permissions ->
                    _uiState.update {
                        it.copy(
                            permissions = permissions,
                            groupedByApp = permissions.groupBy { it.appName },
                            isLoading = false
                        )
                    }
                }
        }
    }
    
    fun togglePermission(permission: AppPermission) {
        viewModelScope.launch {
            val updated = permission.copy(isGranted = !permission.isGranted)
            permissionRepository.updatePermission(updated)
            
            logRepository.logEvent(
                eventType = "PERMISSION_CHANGED",
                data = "${permission.appName} - ${permission.permissionType}: ${updated.isGranted}",
                appPackage = permission.packageName,
                severity = "INFO"
            )
        }
    }
    
    fun refreshPermissions() {
        _uiState.update { it.copy(isLoading = true) }
        syncRealPermissions()
    }
}
