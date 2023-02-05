package com.example.talhamobilecomputing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.talhamobilecomputing.ui.home.HomeScreen
import com.example.talhamobilecomputing.ui.login.LoginScreen
import com.example.talhamobilecomputing.ui.signup.SignUpScreen

@Composable
fun MobileComputingApp(
    appState: MobileComputingAppState = rememberMobileComputingAppState()
){
    NavHost(
        navController = appState.navController,
        startDestination = "login"
    ) {
        composable(route = "login") {
            LoginScreen(navController = appState.navController)
        }
        composable(route = "home"){
            HomeScreen(navController = appState.navController)
        }
        composable(route = "signup"){
            SignUpScreen(navController = appState.navController)
        }
    }
}