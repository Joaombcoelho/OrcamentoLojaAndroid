package com.orcamentoevendas.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.orcamentoevendas.ui.screens.CalculadoraScreen
import com.orcamentoevendas.ui.screens.HistoricoScreen
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipal() {

    val viewModel: CalculadoraViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsState()

    var telaAtual by remember { mutableStateOf("calculadora") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Orçamento e Vendas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { telaAtual = "calculadora" }
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            when (telaAtual) {

                "calculadora" -> CalculadoraScreen(
                    viewModel = viewModel,
                    uiState = uiState,
                    onIrParaHistorico = {
                        telaAtual = "historico"
                    }
                )

                "historico" -> HistoricoScreen(
                    viewModel = viewModel,
                    uiState = uiState,
                    onVoltar = {
                        telaAtual = "calculadora"
                    }
                )
            }
        }
    }
}