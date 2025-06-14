package com.example.registrationcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registrationcompose.ui.composables.Home
import com.example.registrationcompose.ui.composables.LoginScreen
import com.example.registrationcompose.ui.composables.RegisterScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController =  navController, startDestination = "registration") {

        composable("registration") {
            RegisterScreen(navController = navController)
        }

        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            Home()
        }
    }
}