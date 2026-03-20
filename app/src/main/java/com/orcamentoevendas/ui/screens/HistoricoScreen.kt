package com.orcamentoevendas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orcamentoevendas.data.local.entity.OrcamentoEntity
import com.orcamentoevendas.ui.state.CalculadoraUiState
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel
import com.orcamentoevendas.utils.PdfExporter
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.launch

@Composable
fun HistoricoScreen(
    viewModel: CalculadoraViewModel,
    uiState: CalculadoraUiState,
    onVoltar: () -> Unit
) {
    var mostrarDialogLimparHistorico by remember { mutableStateOf(false) }
    var historicoRemovido by remember { mutableStateOf<List<OrcamentoEntity>>(emptyList()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { onVoltar() }) {
                    Text("Voltar")
                }

                OutlinedButton(
                    onClick = { mostrarDialogLimparHistorico = true },
                    enabled = uiState.historico.isNotEmpty()
                ) {
                    Icon(Icons.Default.DeleteSweep, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Limpar histórico")
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "Histórico de Orçamentos",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(uiState.historico) { orcamento ->

                    OrcamentoCard(orcamento)

                }
            }
        }
    }

    if (mostrarDialogLimparHistorico) {
        AlertDialog(
            onDismissRequest = { mostrarDialogLimparHistorico = false },
            title = { Text("Limpar histórico") },
            text = { Text("Deseja remover todos os orçamentos salvos?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        historicoRemovido = uiState.historico.toList()
                        viewModel.limparHistorico()
                        mostrarDialogLimparHistorico = false

                        coroutineScope.launch {
                            val resultado = snackbarHostState.showSnackbar(
                                message = "Histórico limpo",
                                actionLabel = "Desfazer",
                                withDismissAction = true
                            )

                            if (resultado == SnackbarResult.ActionPerformed) {
                                viewModel.restaurarHistorico(historicoRemovido)
                            }

                            historicoRemovido = emptyList()
                        }
                    }
                ) {
                    Text("Limpar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogLimparHistorico = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun OrcamentoCard(item: OrcamentoEntity) {

    val dataFormatada =
        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            .format(Date(item.data))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = item.tipoPeca,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(4.dp))

            Text(item.dimensoes)

            Spacer(Modifier.height(4.dp))

            Text("Comprimento: ${item.comprimento} m")

            Spacer(Modifier.height(8.dp))

            Text("Peso total: ${"%.2f".format(item.pesoTotal)} kg")

            Text("Valor total: R$ ${"%.2f".format(item.valorTotal)}")

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Data: $dataFormatada",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = { PdfExporter.gerarECompartilharPdf(item) },
                modifier = Modifier.align(Alignment.End)
            ) {

                Icon(Icons.Default.Share, contentDescription = "PDF")

                Spacer(Modifier.width(6.dp))

                Text("Compartilhar PDF")
            }
        }
    }
}
