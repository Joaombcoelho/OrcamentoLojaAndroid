package com.example.oramenteevendas.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.oramenteevendas.domain.ResultadoCalculo
import com.example.oramenteevendas.ui.components.ResultadoCard

@Composable
fun HistoricoScreen(historico: List<ResultadoCalculo>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Histórico")

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(historico.reversed()) { item ->
                ResultadoCard(resultado = item)
            }
        }
    }
}