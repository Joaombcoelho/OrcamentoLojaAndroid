package com.orcamentoevendas.ui

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orcamentoevendas.ui.screens.CalculadoraScreen
import com.orcamentoevendas.ui.screens.HistoricoScreen
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel
import com.orcamentoevendas.ui.state.CalculadoraUiState


@Composable
fun TelaPrincipal() {

    var telaAtual by remember { mutableStateOf("calculadora") }

    val viewModel: CalculadoraViewModel = viewModel()
    val uiState = CalculadoraUiState()

    when (telaAtual) {

        "calculadora" -> CalculadoraScreen(
            viewModel = viewModel,
            uiState = uiState,
            onIrParaHistorico = { telaAtual = "historico" }
        )

        "historico" -> HistoricoScreen(
            onVoltar = { telaAtual = "calculadora" }
        )
    }
}