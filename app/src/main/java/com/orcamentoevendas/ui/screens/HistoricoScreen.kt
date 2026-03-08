package com.orcamentoevendas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel
import java.text.NumberFormat
import java.util.Locale
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.orcamentoevendas.utils.PdfExporter

@Composable
fun HistoricoScreen(
    viewModel: CalculadoraViewModel,
    onVoltar: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(onClick = onVoltar) {
                Text("Voltar")
            }

            Button(
                onClick = { viewModel.limparHistorico() }
            ) {
                Text("Limpar histórico")
            }
            val context = LocalContext.current
            Button(
                onClick = {

                    val file = PdfExporter.gerarPdf(context, uiState.historico)

                    val uri = FileProvider.getUriForFile(
                        context,
                        context.packageName + ".provider",
                        file
                    )

                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "application/pdf"
                    intent.putExtra(Intent.EXTRA_STREAM, uri)
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

                    context.startActivity(
                        Intent.createChooser(intent, "Compartilhar PDF")
                    )

                }
            ) {
                Text("Exportar PDF")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(uiState.historico) { item ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            "Peso: %.3f kg".format(item.pesoTotal)
                        )

                        Text(
                            "Valor: ${formatoMoeda.format(item.valorTotal)}"
                        )

                        Text(
                            "Data: ${item.data}"
                        )

                    }

                }

            }

        }

    }

}