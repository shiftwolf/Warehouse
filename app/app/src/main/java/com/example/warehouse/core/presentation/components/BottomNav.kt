package com.example.warehouse.core.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.warehouse.core.presentation.util.View

@Composable
fun BottomNav(
    navController: NavHostController
) {

    val bottomNavItems = listOf(
        View.Orders,
        View.Products,
        View.Customers,
        View.Settings
    )

    BottomNavigation {
        val currentRoute = getCurrentRoute(navController = navController)
        bottomNavItems.forEach { view ->
            BottomNavigationItem(
                icon = { Icon(view.icon, "") },
                label = { Text(view.title) },
                selected = currentRoute == view.route,
                onClick = {
                    if (currentRoute != view.route) {
                        navController.navigate(view.route) }
                }
            )
        }
    }
}

@Composable
private fun getCurrentRoute(navController: NavHostController): String? {
    val backStackEntry by navController.currentBackStackEntryAsState()
    return backStackEntry?.destination?.route
}