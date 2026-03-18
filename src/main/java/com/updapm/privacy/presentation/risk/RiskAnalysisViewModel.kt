package com.updapm.privacy.presentation.risk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.updapm.privacy.data.repository.PermissionRepository
import com.updapm.privacy.util.RiskScoreEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AppRiskInfo(
    val appName: String,
    val packageName: String,
    val riskScore: Int,
    val riskLevel: String,
    val permissionCount: Int
)

data class RiskAnalysisUiState(
    val overallRiskScore: Int = 0,
    val riskLevel: String = "LOW",
    val appRisks: List<AppRiskInfo> = emptyList(),
    val recommendations: List<String> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class RiskAnalysisViewModel @Inject constructor(
    private val permissionRepository: PermissionRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(RiskAnalysisUiState())
    val uiState: StateFlow<RiskAnalysisUiState> = _uiState.asStateFlow()
    
    init {
        syncAndAnalyze()
    }
    
    private fun syncAndAnalyze() {
        viewModelScope.launch {
            try {
                permissionRepository.syncRealPermissions()
            } catch (e: Exception) {
                // Handle error silently
            }
            analyzeRisks()
        }
    }
    
    private fun analyzeRisks() {
        viewModelScope.launch {
            permissionRepository.getAllPermissions().collect { permissions ->
                val groupedByApp = permissions.groupBy { it.packageName }
                val appRisks = groupedByApp.map { (packageName, perms) ->
                    val riskScore = RiskScoreEngine.calculateAppRiskScore(perms)
                    AppRiskInfo(
                        appName = perms.first().appName,
                        packageName = packageName,
                        riskScore = riskScore,
                        riskLevel = RiskScoreEngine.getRiskLevel(100 - riskScore),
                        permissionCount = perms.size
                    )
                }.sortedByDescending { it.riskScore }
                
                val overallScore = RiskScoreEngine.calculateOverallPrivacyScore(permissions)
                
                _uiState.update {
                    it.copy(
                        overallRiskScore = overallScore,
                        riskLevel = RiskScoreEngine.getRiskLevel(overallScore),
                        appRisks = appRisks,
                        recommendations = RiskScoreEngine.getRiskRecommendations(permissions),
                        isLoading = false
                    )
                }
            }
        }
    }
    
    fun refreshAnalysis() {
        _uiState.update { it.copy(isLoading = true) }
        syncAndAnalyze()
    }
}
