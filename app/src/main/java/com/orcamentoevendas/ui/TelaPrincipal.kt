package com.orcamentoevendas.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.orcamentoevendas.ui.screens.CalculadoraScreen
import com.orcamentoevendas.ui.screens.HistoricoScreen
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel

@Composable
fun TelaPrincipal() {

    var telaAtual by remember { mutableStateOf("calculadora") }

    val viewModel: CalculadoraViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (telaAtual) {

        "calculadora" -> CalculadoraScreen(
            viewModel = viewModel,
            uiState = uiState,
            onIrParaHistorico = { telaAtual = "historico" }
        )

        "historico" -> HistoricoScreen(
            viewModel = viewModel,
            uiState = uiState,
            onVoltar = { telaAtual = "calculadora" }
        )
    }
}
