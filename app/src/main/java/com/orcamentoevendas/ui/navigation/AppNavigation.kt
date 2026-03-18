package com.orcamentoevendas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orcamentoevendas.ui.screens.CalculadoraScreen
import com.orcamentoevendas.ui.screens.HistoricoScreen
import com.orcamentoevendas.ui.state.CalculadoraUiState
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel

sealed class Screen(val route: String) {
    data object Calculadora : Screen("calculadora")
    data object Historico : Screen("historico")
}

@Composable
fun AppNavigation(
    viewModel: CalculadoraViewModel,
    uiState: CalculadoraUiState
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Calculadora.route
    ) {
        composable(Screen.Calculadora.route) {
            CalculadoraScreen(
                viewModel = viewModel,
                uiState = uiState,
                onIrParaHistorico = {
                    navController.navigate(Screen.Historico.route)
                }
            )
        }

        composable(Screen.Historico.route) {
            HistoricoScreen(
                viewModel = viewModel,
                uiState = uiState,
                onVoltar = {
                    navController.popBackStack()
                }
            )
        }
    }
}
