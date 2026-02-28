package com.example.oramenteevendas.ui.navigation

sealed class Screen(val route: String) {
    object Calculadora : Screen("calculadora")
    object Historico : Screen("historico")
}