package com.example.oramenteevendas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.oramenteevendas.domain.CalculadoraPeso
import com.example.oramenteevendas.domain.ResultadoCalculo
import com.example.oramenteevendas.ui.components.ResultadoCard
import com.example.oramenteevendas.utils.Densidades
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oramenteevendas.ui.viewmodel.CalculadoraViewModel
import androidx.compose.runtime.collectAsState



fun String.somenteNumeros(): String {
    return this
        .replace(",", ".")
        .filter { it.isDigit() || it == '.' }
}
@Composable
fun CalculadoraScreen(
    viewModel: CalculadoraViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsState()

    val resultado = state.resultado
    val historico = state.historico

    var comprimento by remember { mutableStateOf("") }
    var largura by remember { mutableStateOf("") }
    var espessura by remember { mutableStateOf("") }
    var precoKg by remember { mutableStateOf("") }
    var tipoPeca by remember { mutableStateOf("Chapa") }
    var baseAba by remember { mutableStateOf("") }
    var retorno by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("1") }


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

                    val selecionado = tipoPeca == tipo

                    Button(
                        onClick = {
                            tipoPeca = tipo
                            largura = ""
                            espessura = ""
                            baseAba = ""
                            retorno = ""
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selecionado)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Text(
                            text = tipo,
                            color = if (selecionado)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant
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

        when (tipoPeca) {

            "Chapa" -> {
                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it.somenteNumeros() },
                    label = { Text("Largura (m)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it.somenteNumeros() },
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }

            "Tubo Quadrado" -> {
                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it.somenteNumeros() },
                    label = { Text("Lado Externo (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it.somenteNumeros() },
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }

            "Tubo Retangular" -> {
                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it.somenteNumeros() },
                    label = { Text("Base (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = baseAba,
                    onValueChange = { baseAba = it.somenteNumeros() },
                    label = { Text("Altura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it.somenteNumeros() },
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }

            "Viga U" -> {
                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it.somenteNumeros() },
                    label = { Text("Altura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = baseAba,
                    onValueChange = { baseAba = it.somenteNumeros() },
                    label = { Text("Base da Aba (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it.somenteNumeros() },
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }

            "Viga U Enrijecida" -> {
                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it.somenteNumeros() },
                    label = { Text("Altura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = baseAba,
                    onValueChange = { baseAba = it.somenteNumeros() },
                    label = { Text("Base da Aba (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = retorno,
                    onValueChange = { retorno = it.somenteNumeros() },
                    label = { Text("Retorno (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it.somenteNumeros() },
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }

            "Tubo Redondo" -> {
                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it.somenteNumeros() },
                    label = { Text("Diâmetro (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it.somenteNumeros() },
                    label = { Text("Espessura (mm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = comprimento,
            onValueChange = { comprimento = it.somenteNumeros() },
            label = { Text("Comprimento (m)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it.somenteNumeros() },
            label = { Text("Quantidade") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precoKg,
            onValueChange = { precoKg = it.somenteNumeros() },
            label = { Text("Preço por Kg (opcional)") },
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                    )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                val comp = comprimento.toDoubleOrNull()
                val larg = largura.toDoubleOrNull()
                val espMm = espessura.toDoubleOrNull()
                val baseMm = baseAba.toDoubleOrNull()
                val retornoMm = retorno.toDoubleOrNull()
                val qtd = quantidade.toIntOrNull() ?: 1

                if (comp == null || larg == null || espMm == null) {

                    return@Button
                }

                val espM = espMm / 1000.0
                val densidade = Densidades.ACO

                val peso = when (tipoPeca) {

                    "Chapa" ->
                        CalculadoraPeso.calcularChapa(comp, larg, espM, densidade)

                    "Tubo Quadrado" -> {
                        val ladoM = larg / 1000.0
                        CalculadoraPeso.calcularTuboQuadrado(ladoM, espM, comp, densidade)
                    }

                    "Tubo Retangular" -> {
                        if (baseMm == null) return@Button
                        val baseM = larg / 1000.0
                        val alturaM = baseMm / 1000.0
                        CalculadoraPeso.calcularTuboRetangular(
                            baseM, alturaM, espM, comp, densidade
                        )
                    }

                    "Viga U" -> {
                        if (baseMm == null) return@Button
                        val alturaM = larg / 1000.0
                        val baseM = baseMm / 1000.0
                        CalculadoraPeso.calcularVigaU(
                            alturaM, baseM, espM, comp, densidade
                        )
                    }

                    "Viga U Enrijecida" -> {
                        if (baseMm == null || retornoMm == null) return@Button
                        val alturaM = larg / 1000.0
                        val baseM = baseMm / 1000.0
                        val retornoM = retornoMm / 1000.0
                        CalculadoraPeso.calcularVigaUEnrijecida(
                            alturaM, baseM, retornoM, espM, comp, densidade
                        )
                    }

                    "Tubo Redondo" -> {
                        val diametroM = larg / 1000.0
                        CalculadoraPeso.calcularTuboRedondo(
                            diametroM, espM, comp, densidade
                        )
                    }

                    else -> 0.0
                }

                val preco = precoKg.replace(",", ".").toDoubleOrNull()

                viewModel.salvarResultado(
                    pesoUnitario = peso,
                    quantidade = qtd,
                    precoKg = preco
                )
            }
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        resultado?.let {
            ResultadoCard(resultado = it)
        }

        Spacer(modifier = Modifier.height(24.dp))

        }
    }

