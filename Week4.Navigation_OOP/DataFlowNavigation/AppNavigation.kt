package com.example.dataflownavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "forgot") {

        // Màn 1: có 3 tham số tùy chọn để nhận dữ liệu từ màn 4
        composable(
            route = "forgot?email={email}&otp={otp}&pass={pass}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType; defaultValue = "" },
                navArgument("otp") { type = NavType.StringType; defaultValue = "" },
                navArgument("pass") { type = NavType.StringType; defaultValue = "" }
            )
        ) { entry ->
            ForgotPasswordScreen(
                receivedEmail = entry.arguments?.getString("email") ?: "",
                receivedOtp = entry.arguments?.getString("otp") ?: "",
                receivedPass = entry.arguments?.getString("pass") ?: "",
                onNext = { email -> navController.navigate("verify/$email") }
            )
        }

        // Màn 2: nhận email
        composable("verify/{email}") { entry ->
            val email = entry.arguments?.getString("email") ?: ""
            VerificationScreen(
                email = email,
                onBackClick = { navController.popBackStack() },
                onNext = { otp -> navController.navigate("reset/$email/$otp") }
            )
        }

        // Màn 3: nhận email, otp
        composable("reset/{email}/{otp}") { entry ->
            val email = entry.arguments?.getString("email") ?: ""
            val otp = entry.arguments?.getString("otp") ?: ""
            ResetPasswordScreen(
                onBackClick = { navController.popBackStack() },
                onNext = { pass -> navController.navigate("confirm/$email/$otp/$pass") }
            )
        }

        // Màn 4: nhận cả 3
        composable("confirm/{email}/{otp}/{pass}") { entry ->
            val email = entry.arguments?.getString("email") ?: ""
            val otp = entry.arguments?.getString("otp") ?: ""
            val pass = entry.arguments?.getString("pass") ?: ""
            ConfirmScreen(
                email = email,
                otp = otp,
                pass = pass,
                onBackClick = { navController.popBackStack() },
                onSubmit = {
                    // Về màn 1 kèm cả 3 dữ liệu, xóa các màn giữa khỏi back stack
                    navController.navigate("forgot?email=$email&otp=$otp&pass=$pass") {
                        popUpTo("forgot?email={email}&otp={otp}&pass={pass}") { inclusive = true }
                    }
                }
            )
        }
    }
}