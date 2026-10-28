package com.example.uth_todo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uth_todo.intro.OnboardingScreen
import com.example.uth_todo.intro.SplashScreen
import com.example.uth_todo.intro.onboardingPages

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onFinished = {
                navController.navigate("onboarding1") {
                    popUpTo("splash") {inclusive = true}
                }
            })
        }
        composable("onboarding1") {
            OnboardingScreen(page = onboardingPages[0],step = 0, onNextClick =  { navController.navigate("onboarding2") }, onSkipClick = {navController.navigate("home")})
        }
        composable("onboarding2") {
            OnboardingScreen(page = onboardingPages[1],step = 1, onNextClick =  { navController.navigate("onboarding3") }, onSkipClick = {navController.navigate("home")})
        }
        composable("onboarding3") {
            OnboardingScreen(page = onboardingPages[2],step = 2, onNextClick =  { navController.navigate("home") }, onSkipClick = {navController.navigate("home")})
        }
        composable("home") {
            Text("Tạm home")
        }
    }


}