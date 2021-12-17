package ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.WindowState
import navcontroller.NavController
import navcontroller.NavigationHost
import navcontroller.composable
import ui.screens.staffscreens.staffs.ui.StaffsScreen
import ui.screens.demoscreens.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun CustomNavHost(
    navController: NavController,
    windowState: WindowState
) {
    NavigationHost(navController) {
        composable(Screens.HomeScreen) {
            HomeScreen(it, navController)
        }
        composable(Screens.CustomersScreen) {
            CustomersScreen(it, navController)
        }
        composable(Screens.StaffsScreen) {
            StaffsScreen(it, navController)
        }
        composable(Screens.ServicesScreen) {
            ServicesScreen(it, navController)
        }
        composable(Screens.SettingsScreen) {
            SettingsScreen(it, navController)
        }
    }.build()
}