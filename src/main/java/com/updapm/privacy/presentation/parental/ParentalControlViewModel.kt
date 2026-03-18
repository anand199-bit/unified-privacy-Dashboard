package com.updapm.privacy.presentation.parental

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.updapm.privacy.data.local.entity.ParentalControl
import com.updapm.privacy.data.repository.ParentalControlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ParentalControlUiState(
    val controls: List<ParentalControl> = emptyList(),
    val blockedCount: Int = 0,
    val isLoading: Boolean = true
)

@HiltViewModel
class ParentalControlViewModel @Inject constructor(
    private val parentalControlRepository: ParentalControlRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ParentalControlUiState())
    val uiState: StateFlow<ParentalControlUiState> = _uiState.asStateFlow()
    
    init {
        syncInstalledApps()
        loadControls()
    }
    
    private fun syncInstalledApps() {
        viewModelScope.launch {
            try {
                parentalControlRepository.syncInstalledApps()
            } catch (e: Exception) {
                // Handle error silently
            }
        }
    }
    
    private fun loadControls() {
        viewModelScope.launch {
            parentalControlRepository.getAllControls()
                .collect { controls ->
                    _uiState.update {
                        it.copy(
                            controls = controls,
                            blockedCount = controls.count { it.isBlocked },
                            isLoading = false
                        )
                    }
                }
        }
    }
    
    fun toggleBlock(control: ParentalControl) {
        viewModelScope.launch {
            val updated = control.copy(isBlocked = !control.isBlocked)
            parentalControlRepository.updateControl(updated)
        }
    }
    
    fun updateTimeLimit(control: ParentalControl, minutes: Int) {
        viewModelScope.launch {
            val updated = control.copy(timeLimit = minutes)
            parentalControlRepository.updateControl(updated)
        }
    }
    
    fun refreshApps() {
        _uiState.update { it.copy(isLoading = true) }
        syncInstalledApps()
    }
}
