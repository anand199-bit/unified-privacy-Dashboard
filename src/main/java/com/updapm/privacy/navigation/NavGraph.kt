package com.updapm.privacy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.updapm.privacy.presentation.compliance.ComplianceScreen
import com.updapm.privacy.presentation.dashboard.DashboardScreen
import com.updapm.privacy.presentation.logs.EncryptedLogsScreen
import com.updapm.privacy.presentation.parental.ParentalControlScreen
import com.updapm.privacy.presentation.permissions.PermissionManagerScreen
import com.updapm.privacy.presentation.risk.RiskAnalysisScreen

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Permissions : Screen("permissions")
    object Risk : Screen("risk")
    object Logs : Screen("logs")
    object Parental : Screen("parental")
    object Compliance : Screen("compliance")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToPermissions = { navController.navigate(Screen.Permissions.route) },
                onNavigateToRisk = { navController.navigate(Screen.Risk.route) },
                onNavigateToLogs = { navController.navigate(Screen.Logs.route) },
                onNavigateToParental = { navController.navigate(Screen.Parental.route) },
                onNavigateToCompliance = { navController.navigate(Screen.Compliance.route) }
            )
        }
        
        composable(Screen.Permissions.route) {
            PermissionManagerScreen(
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Risk.route) {
            RiskAnalysisScreen(
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Logs.route) {
            EncryptedLogsScreen(
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Parental.route) {
            ParentalControlScreen(
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Compliance.route) {
            ComplianceScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
