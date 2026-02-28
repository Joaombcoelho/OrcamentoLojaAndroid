package com.example.oramenteevendas.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.oramenteevendas.domain.ResultadoCalculo
import com.example.oramenteevendas.ui.screens.CalculadoraScreen
import com.example.oramenteevendas.ui.screens.HistoricoScreen
import androidx.navigation.NavHostController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    var historico by remember { mutableStateOf(listOf<ResultadoCalculo>()) }

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = currentRoute(navController) == Screen.Calculadora.route,
                    onClick = {
                        navController.navigate(Screen.Calculadora.route)
                    },
                    label = { Text("Calculadora") },
                    icon = {}
                )

                NavigationBarItem(
                    selected = currentRoute(navController) == Screen.Historico.route,
                    onClick = {
                        navController.navigate(Screen.Historico.route)
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
                CalculadoraScreen(
                    onNovoResultado = { novo ->
                        historico = historico + novo
                    }
                )
            }

            composable(Screen.Historico.route) {
                HistoricoScreen(historico = historico)
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}