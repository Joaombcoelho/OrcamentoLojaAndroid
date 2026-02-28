package com.example.oramenteevendas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oramenteevendas.ui.viewmodel.CalculadoraViewModel
import com.example.oramenteevendas.ui.components.ResultadoCard

@Composable
fun HistoricoScreen(viewModel: CalculadoraViewModel) {

    val lista by viewModel.historico.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Histórico") })
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(lista) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Comprimento: ${item.comprimento}")
                        Text("Largura: ${item.largura}")
                        Text("Valor Unitário: ${item.valorUnitario}")
                        Text("Resultado: ${item.resultado}")
                    }
                }
            }
        }
    }
}