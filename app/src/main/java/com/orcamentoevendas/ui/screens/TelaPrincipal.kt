package com.orcamentoevendas.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api

import com.orcamentoevendas.ui.screens.CalculadoraScreen
import com.orcamentoevendas.ui.screens.HistoricoScreen
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModelFactory
import com.orcamentoevendas.data.local.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipal() {

    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val dao = database.resultadoDao()

    val factory = CalculadoraViewModelFactory(dao)

    val viewModel: CalculadoraViewModel =
        viewModel(factory = factory)

    var telaAtual by remember { mutableStateOf("calculadora") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Orçamento e Vendas") }
            )
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
                    onIrParaHistorico = { telaAtual = "historico" }
                )

                "historico" -> HistoricoScreen(
                    viewModel = viewModel,
                    onVoltar = { telaAtual = "calculadora" }
                )

            }

        }

    }
}