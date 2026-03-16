package com.orcamentoevendas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.orcamentoevendas.ui.components.ResultadoCard
import com.orcamentoevendas.ui.state.CalculadoraUiState
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel

@Composable
fun CalculadoraScreen(
    viewModel: CalculadoraViewModel,
    uiState: CalculadoraUiState,
    onIrParaHistorico: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text("Calculadora de Peso")

        Spacer(modifier = Modifier.height(16.dp))

        val tipos = listOf(
            "Chapa",
            "Tubo Quadrado",
            "Tubo Retangular",
            "Viga U",
            "Viga U Enrijecida",
            "Tubo Redondo"
        )

        val linhas = tipos.chunked(2)

        linhas.forEach { linha ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                linha.forEach { tipo ->
                    val selecionado = uiState.tipoPeca == tipo

                    Button(
                        onClick = { viewModel.atualizarTipoPeca(tipo) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (selecionado) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.surfaceVariant
                                }
                        )
                    ) {
                        Text(
                            text = tipo,
                            color =
                                if (selecionado) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                        )
                    }
                }

                if (linha.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState.tipoPeca) {
            "Chapa" -> {
                OutlinedTextField(
                    value = uiState.largura,
                    onValueChange = viewModel::atualizarLargura,
                    label = { Text("Largura (m)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.espessura,
                    onValueChange = viewModel::atualizarEspessura,
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }

            "Tubo Quadrado" -> {
                OutlinedTextField(
                    value = uiState.largura,
                    onValueChange = viewModel::atualizarLargura,
                    label = { Text("Lado Externo (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.espessura,
                    onValueChange = viewModel::atualizarEspessura,
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }

            "Tubo Retangular" -> {
                OutlinedTextField(
                    value = uiState.largura,
                    onValueChange = viewModel::atualizarLargura,
                    label = { Text("Base (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.baseAba,
                    onValueChange = viewModel::atualizarBaseAba,
                    label = { Text("Altura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.espessura,
                    onValueChange = viewModel::atualizarEspessura,
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }

            "Viga U" -> {
                OutlinedTextField(
                    value = uiState.largura,
                    onValueChange = viewModel::atualizarLargura,
                    label = { Text("Altura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.baseAba,
                    onValueChange = viewModel::atualizarBaseAba,
                    label = { Text("Base da Aba (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.espessura,
                    onValueChange = viewModel::atualizarEspessura,
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }

            "Viga U Enrijecida" -> {
                OutlinedTextField(
                    value = uiState.largura,
                    onValueChange = viewModel::atualizarLargura,
                    label = { Text("Altura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.baseAba,
                    onValueChange = viewModel::atualizarBaseAba,
                    label = { Text("Base da Aba (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.retorno,
                    onValueChange = viewModel::atualizarRetorno,
                    label = { Text("Retorno (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.espessura,
                    onValueChange = viewModel::atualizarEspessura,
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }

            "Tubo Redondo" -> {
                OutlinedTextField(
                    value = uiState.largura,
                    onValueChange = viewModel::atualizarLargura,
                    label = { Text("Diâmetro (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = uiState.espessura,
                    onValueChange = viewModel::atualizarEspessura,
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.comprimento,
            onValueChange = viewModel::atualizarComprimento,
            label = { Text("Comprimento (m)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        OutlinedTextField(
            value = uiState.quantidade,
            onValueChange = viewModel::atualizarQuantidade,
            label = { Text("Quantidade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.precoKg,
            onValueChange = viewModel::atualizarPrecoKg,
            label = { Text("Preço por Kg (opcional)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.calcularResultado() }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        uiState.resultadoAtual?.let { pesoCalculado ->
            val preco = uiState.precoKg.replace(",", ".").toDoubleOrNull() ?: 0.0
            val valorTotal = pesoCalculado * preco

            ResultadoCard(
                peso = pesoCalculado,
                valorTotal = valorTotal
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = { viewModel.salvarResultadoAtual() }
            ) {
                Text("Salvar no Histórico")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onIrParaHistorico() }) {
            Text("Ver Histórico")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
