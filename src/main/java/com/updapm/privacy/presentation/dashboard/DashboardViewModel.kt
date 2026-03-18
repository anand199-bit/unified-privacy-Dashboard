package com.updapm.privacy.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.updapm.privacy.data.local.entity.AppPermission
import com.updapm.privacy.data.repository.PermissionRepository
import com.updapm.privacy.util.RiskScoreEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val privacyScore: Int = 0,
    val totalApps: Int = 0,
    val highRiskApps: Int = 0,
    val permissions: List<AppPermission> = emptyList(),
    val recommendations: List<String> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val permissionRepository: PermissionRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
    
    init {
        syncRealData()
        loadDashboardData()
    }
    
    private fun syncRealData() {
        viewModelScope.launch {
            try {
                permissionRepository.syncRealPermissions()
            } catch (e: Exception) {
                // Handle error silently, will show empty state
            }
        }
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            permissionRepository.getAllPermissions()
                .collect { permissions ->
                    val privacyScore = RiskScoreEngine.calculateOverallPrivacyScore(permissions)
                    val highRiskApps = permissions
                        .groupBy { it.packageName }
                        .count { (_, perms) -> 
                            RiskScoreEngine.calculateAppRiskScore(perms) > 50 
                        }
                    
                    _uiState.update {
                        it.copy(
                            privacyScore = privacyScore,
                            totalApps = permissions.groupBy { it.packageName }.size,
                            highRiskApps = highRiskApps,
                            permissions = permissions,
                            recommendations = RiskScoreEngine.getRiskRecommendations(permissions),
                            isLoading = false
                        )
                    }
                }
        }
    }
    
    fun refreshData() {
        _uiState.update { it.copy(isLoading = true) }
        syncRealData()
    }
}
