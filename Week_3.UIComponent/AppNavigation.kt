package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onOpenClick = {navController.navigate("component")})
        }
        composable("component") {
            ComponentScreen(
                onBackClick = {navController.popBackStack()},
                onItemClick = {route -> navController.navigate(route) }
            )
        }
        composable("textDetail") {
            TextDetailScreen(
                onBackClick = {navController.popBackStack()}
            )
        }
        composable("columnDetail") {
            ColumnDetail(
                onBackClick = {navController.popBackStack()}
            )
        }
        composable("imageDetail") {
            ImageDetailScreen(
                onBackClick = {navController.popBackStack()}
            )
        }
        composable("passwordFieldDetail") {
            PasswordDetail(
                onBackClick = {navController.popBackStack()}
            )
        }
        composable("rowDetail") {
            RowDetail(
                onBackClick = {navController.popBackStack()}
            )
        }
        composable("textFieldDetail") {
            TextFieldDetail(
                onBackClick = {navController.popBackStack()}
            )
        }
    }
}