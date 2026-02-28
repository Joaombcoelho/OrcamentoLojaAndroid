package com.example.oramenteevendas.ui.navigation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oramenteevendas.ui.viewmodel.CalculadoraViewModel
sealed class Screen(val route: String) {
    object Calculadora : Screen("calculadora")
    object Historico : Screen("historico")
}