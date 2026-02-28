package com.example.oramenteevendas.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.oramenteevendas.ui.screens.CalculadoraScreen
import com.example.oramenteevendas.ui.screens.HistoricoScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oramenteevendas.ui.viewmodel.CalculadoraViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: CalculadoraViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = currentRoute == Screen.Calculadora.route,
                    onClick = {
                        navController.navigate(Screen.Calculadora.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    label = { Text("Calculadora") },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentRoute == Screen.Historico.route,
                    onClick = {
                        navController.navigate(Screen.Historico.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    label = { Text("Histórico") },
                    icon = {}
                )
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screen.Calculadora.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Screen.Calculadora.route) {
                CalculadoraScreen(viewModel = viewModel)
            }

            composable(Screen.Historico.route) {
                HistoricoScreen(viewModel = viewModel)
            }
        }
    }
}