package com.updapm.privacy.presentation.logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.updapm.privacy.data.local.entity.EncryptedLog
import com.updapm.privacy.data.repository.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LogsUiState(
    val logs: List<EncryptedLog> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class LogsViewModel @Inject constructor(
    private val logRepository: LogRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(LogsUiState())
    val uiState: StateFlow<LogsUiState> = _uiState.asStateFlow()
    
    init {
        generateInitialLogs()
        loadLogs()
    }
    
    private fun generateInitialLogs() {
        viewModelScope.launch {
            // Log app startup
            logRepository.logEvent(
                eventType = "APP_START",
                data = "UPDAPM Privacy Manager started",
                appPackage = "com.updapm.privacy",
                severity = "INFO"
            )
        }
    }
    
    private fun loadLogs() {
        viewModelScope.launch {
            logRepository.getRecentLogs()
                .collect { logs ->
                    _uiState.update {
                        it.copy(
                            logs = logs,
                            isLoading = false
                        )
                    }
                }
        }
    }
    
    fun clearLogs() {
        viewModelScope.launch {
            logRepository.clearAllLogs()
        }
    }
}
