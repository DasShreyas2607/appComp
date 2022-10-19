package com.example.jetpackcomposedemo

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposedemo.navigation.Actions
import com.example.jetpackcomposedemo.navigation.Destination
import com.example.jetpackcomposedemo.ui.DashboardScreen
import com.example.jetpackcomposedemo.ui.LoginScreen
import com.example.jetpackcomposedemo.ui.showcart

@Composable
fun ComposeApp(
    cartViewModel: CartViewModel = viewModel()
) {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    MaterialTheme {
        NavHost(navController = navController, startDestination = Destination.Login) {

            composable(Destination.Login) {
                LoginScreen(actions.openDashboard)
            }

            composable(Destination.DashBoard) {
                DashboardScreen(cartViewModel=cartViewModel)
            }
            composable("toshowcart"){
                showcart(cartViewModel=cartViewModel)
            }
        }
    }
}