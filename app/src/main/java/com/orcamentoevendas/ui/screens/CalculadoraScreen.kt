package com.orcamentoevendas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
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
import com.orcamentoevendas.domain.TipoPeca
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

        MaterialSelector(
            materialSelecionado = uiState.material,
            onMaterialSelecionado = viewModel::atualizarMaterial
        )

        Spacer(modifier = Modifier.height(16.dp))

        TipoPecaSelector(
            tipoSelecionado = uiState.tipoPeca,
            onTipoSelecionado = viewModel::atualizarTipoPeca
        )

        Spacer(modifier = Modifier.height(16.dp))

        CamposPorTipoPeca(
            tipoPeca = uiState.tipoPeca,
            uiState = uiState,
            viewModel = viewModel
        )

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

        uiState.mensagemErro?.let { erro ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = erro,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

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

@Composable
private fun MaterialSelector(
    materialSelecionado: String,
    onMaterialSelecionado: (String) -> Unit
) {
    val materiais = listOf("Aço", "Inox", "Alumínio")

    Text("Material", style = MaterialTheme.typography.titleSmall)
    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        materiais.forEach { material ->
            val selecionado = materialSelecionado == material
            Button(
                onClick = { onMaterialSelecionado(material) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selecionado) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text(
                    material,
                    color =
                        if (selecionado) MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun TipoPecaSelector(
    tipoSelecionado: TipoPeca,
    onTipoSelecionado: (TipoPeca) -> Unit
) {
    val linhas = TipoPeca.entries.chunked(2)

    linhas.forEach { linha ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            linha.forEach { tipo ->
                val selecionado = tipoSelecionado == tipo

                Button(
                    onClick = { onTipoSelecionado(tipo) },
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
                        text = tipo.label,
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
}

@Composable
private fun CamposPorTipoPeca(
    tipoPeca: TipoPeca,
    uiState: CalculadoraUiState,
    viewModel: CalculadoraViewModel
) {
    when (tipoPeca) {
        TipoPeca.CHAPA -> {
            CampoNumerico(uiState.largura, viewModel::atualizarLargura, "Largura (m)")
            CampoNumerico(uiState.espessura, viewModel::atualizarEspessura, "Espessura (mm)")
        }

        TipoPeca.TUBO_QUADRADO -> {
            CampoNumerico(uiState.largura, viewModel::atualizarLargura, "Lado Externo (mm)")
            CampoNumerico(uiState.espessura, viewModel::atualizarEspessura, "Espessura (mm)")
        }

        TipoPeca.TUBO_RETANGULAR -> {
            CampoNumerico(uiState.largura, viewModel::atualizarLargura, "Base (mm)")
            CampoNumerico(uiState.baseAba, viewModel::atualizarBaseAba, "Altura (mm)")
            CampoNumerico(uiState.espessura, viewModel::atualizarEspessura, "Espessura (mm)")
        }

        TipoPeca.VIGA_U -> {
            CampoNumerico(uiState.largura, viewModel::atualizarLargura, "Altura (mm)")
            CampoNumerico(uiState.baseAba, viewModel::atualizarBaseAba, "Base da Aba (mm)")
            CampoNumerico(uiState.espessura, viewModel::atualizarEspessura, "Espessura (mm)")
        }

        TipoPeca.VIGA_U_ENRIJECIDA -> {
            CampoNumerico(uiState.largura, viewModel::atualizarLargura, "Altura (mm)")
            CampoNumerico(uiState.baseAba, viewModel::atualizarBaseAba, "Base da Aba (mm)")
            CampoNumerico(uiState.retorno, viewModel::atualizarRetorno, "Retorno (mm)")
            CampoNumerico(uiState.espessura, viewModel::atualizarEspessura, "Espessura (mm)")
        }

        TipoPeca.TUBO_REDONDO -> {
            CampoNumerico(uiState.largura, viewModel::atualizarLargura, "Diâmetro (mm)")
            CampoNumerico(uiState.espessura, viewModel::atualizarEspessura, "Espessura (mm)")
        }
    }
}

@Composable
private fun CampoNumerico(
    valor: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Decimal
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}
