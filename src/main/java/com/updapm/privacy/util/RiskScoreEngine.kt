package com.updapm.privacy.util

import com.updapm.privacy.data.local.entity.AppPermission

object RiskScoreEngine {
    
    private val HIGH_RISK_PERMISSIONS = setOf(
        "android.permission.READ_CONTACTS",
        "android.permission.READ_SMS",
        "android.permission.ACCESS_FINE_LOCATION",
        "android.permission.CAMERA",
        "android.permission.RECORD_AUDIO",
        "android.permission.READ_CALL_LOG"
    )
    
    private val MEDIUM_RISK_PERMISSIONS = setOf(
        "android.permission.ACCESS_COARSE_LOCATION",
        "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE",
        "android.permission.GET_ACCOUNTS"
    )
    
    fun calculateAppRiskScore(permissions: List<AppPermission>): Int {
        var score = 0
        
        permissions.forEach { permission ->
            if (permission.isGranted) {
                score += when {
                    HIGH_RISK_PERMISSIONS.contains(permission.permissionType) -> 15
                    MEDIUM_RISK_PERMISSIONS.contains(permission.permissionType) -> 8
                    else -> 3
                }
                
                // Add score based on access frequency
                score += (permission.accessCount / 10).coerceAtMost(10)
            }
        }
        
        return score.coerceIn(0, 100)
    }
    
    fun calculateOverallPrivacyScore(allPermissions: List<AppPermission>): Int {
        if (allPermissions.isEmpty()) return 100
        
        var totalRisk = 0
        allPermissions.forEach { permission ->
            if (permission.isGranted) {
                totalRisk += when {
                    HIGH_RISK_PERMISSIONS.contains(permission.permissionType) -> 10
                    MEDIUM_RISK_PERMISSIONS.contains(permission.permissionType) -> 5
                    else -> 2
                }
            }
        }
        
        val maxPossibleRisk = allPermissions.size * 10
        val privacyScore = 100 - ((totalRisk.toFloat() / maxPossibleRisk.coerceAtLeast(1)) * 100).toInt()
        
        return privacyScore.coerceIn(0, 100)
    }
    
    fun getRiskLevel(score: Int): String {
        return when {
            score >= 70 -> "LOW"
            score >= 40 -> "MEDIUM"
            else -> "HIGH"
        }
    }
    
    fun getRiskRecommendations(permissions: List<AppPermission>): List<String> {
        val recommendations = mutableListOf<String>()
        
        val highRiskGranted = permissions.filter { 
            it.isGranted && HIGH_RISK_PERMISSIONS.contains(it.permissionType) 
        }
        
        if (highRiskGranted.isNotEmpty()) {
            recommendations.add("⚠️ ${highRiskGranted.size} high-risk permissions detected")
            recommendations.add("🔒 Consider revoking unused sensitive permissions")
        }
        
        val frequentAccess = permissions.filter { it.accessCount > 50 }
        if (frequentAccess.isNotEmpty()) {
            recommendations.add("📊 ${frequentAccess.size} apps accessing data frequently")
        }
        
        if (recommendations.isEmpty()) {
            recommendations.add("✅ Your privacy settings look good!")
        }
        
        return recommendations
    }
}
