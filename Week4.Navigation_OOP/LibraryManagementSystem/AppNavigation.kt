package com.example.librarymanagementsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.librarymanagementsystem.Screen.BookListScreen
import com.example.librarymanagementsystem.Screen.ManageScreen
import com.example.librarymanagementsystem.Screen.StudentListScreen

// Mô tả một tab: route, chữ, icon (cùng ý với ComponentItem)
data class TabItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val tabs = listOf(
    TabItem("manage", "Quản lý", Icons.Filled.Home),
    TabItem("books", "DS Sách", Icons.Filled.Star),
    TabItem("students", "Sinh viên", Icons.Filled.Person)
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val library = remember { Library() }   // một Library dùng chung cho 3 tab

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo("manage")
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "manage",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("manage") { ManageScreen(library) }
            composable("books") { BookListScreen(library) }
            composable("students") { StudentListScreen(library) }
        }
    }
}