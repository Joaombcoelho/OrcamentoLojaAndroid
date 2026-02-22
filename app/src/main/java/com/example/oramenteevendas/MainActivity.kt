package com.example.oramenteevendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.oramenteevendas.ui.theme.OrçamenteEVendasTheme
import android.util.Log
import com.example.oramenteevendas.utils.CalculadoraPeso
import com.example.oramenteevendas.utils.Densidades
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            OrçamenteEVendasTheme {
                CalculadoraScreen()
            }
        }
    }
}


@Composable
fun CalculadoraScreen() {

    var comprimento by remember { mutableStateOf("") }
    var largura by remember { mutableStateOf("") }
    var espessura by remember { mutableStateOf("") }
    var precoKg by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var materialSelecionado by remember { mutableStateOf("Aço") }
    var tipoPeca by remember { mutableStateOf("Chapa") }
    var baseAba by remember { mutableStateOf("") }
    var retorno by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text("Calculadora de Peso")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tipo de Peça")

        Spacer(modifier = Modifier.height(8.dp))

        val tipos = listOf(
            "Chapa",
            "Tubo Quadrado",
            "Tubo Retangular",
            "Viga U",
            "Viga U Enrijecida"
        )

        val linhas = tipos.chunked(2)

        linhas.forEach { linha ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                linha.forEach { tipo ->

                    val selecionado = tipoPeca == tipo

                    val corFundo by animateColorAsState(
                        targetValue = if (selecionado)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surfaceVariant,
                        label = ""
                    )

                    val corTexto by animateColorAsState(
                        targetValue = if (selecionado)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        label = ""
                    )

                    val corBorda by animateColorAsState(
                        targetValue = if (selecionado)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outline,
                        label = ""
                    )

                    Button(
                        onClick = { tipoPeca = tipo },
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                width = 2.dp,
                                color = corBorda,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = corFundo,
                            contentColor = corTexto
                        )
                    ) {
                        Text(tipo)
                    }
                }

                if (linha.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CAMPOS DINÂMICOS

        when (tipoPeca) {

            "Chapa" -> {

                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it },
                    label = { Text("Largura (m)") }
                )

                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it },
                    label = { Text("Espessura (mm)") }
                )
            }

            "Tubo Quadrado" -> {

                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it },
                    label = { Text("Lado Externo (mm)") }
                )

                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it },
                    label = { Text("Espessura (mm)") }
                )
            }

            "Tubo Retangular" -> {

                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it },
                    label = { Text("Base (mm)") }
                )

                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it },
                    label = { Text("Altura (mm)") }
                )
            }

            "Viga U" -> {

                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it },
                    label = { Text("Altura (mm)") }
                )

                OutlinedTextField(
                    value = baseAba,
                    onValueChange = { baseAba = it },
                    label = { Text("Base da Aba (mm)") }
                )

                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it },
                    label = { Text("Espessura (mm)") }
                )
            }

            "Viga U Enrijecida" -> {

                OutlinedTextField(
                    value = largura,
                    onValueChange = { largura = it },
                    label = { Text("Altura (mm)") }
                )

                OutlinedTextField(
                    value = baseAba,
                    onValueChange = { baseAba = it },
                    label = { Text("Base da Aba (mm)") }
                )

                OutlinedTextField(
                    value = retorno,
                    onValueChange = { retorno = it },
                    label = { Text("Retorno (mm)") }
                )

                OutlinedTextField(
                    value = espessura,
                    onValueChange = { espessura = it },
                    label = { Text("Espessura (mm)") }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Comprimento SEMPRE por último
        OutlinedTextField(
            value = comprimento,
            onValueChange = { comprimento = it },
            label = { Text("Comprimento (m)") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precoKg,
            onValueChange = { precoKg = it },
            label = { Text("Preço por Kg (opcional)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                val comp = comprimento.toDoubleOrNull()
                val larg = largura.toDoubleOrNull()
                val espMm = espessura.toDoubleOrNull()
                val preco = precoKg.toDoubleOrNull()
                val baseMm = baseAba.toDoubleOrNull()
                val retornoMm = retorno.toDoubleOrNull()

                if (comp != null && larg != null && espMm != null) {

                    val espM = espMm / 1000.0

                    val densidade = when (materialSelecionado) {
                        "Inox" -> Densidades.INOX
                        "Galvanizado" -> Densidades.GALVANIZADO
                        else -> Densidades.ACO
                    }

                    val peso = when (tipoPeca) {

                        "Chapa" -> {
                            CalculadoraPeso.calcularChapa(
                                comp,
                                larg,
                                espM,
                                densidade
                            )
                        }

                        "Tubo Quadrado" -> {
                            val ladoM = larg / 1000.0
                            CalculadoraPeso.calcularTuboQuadrado(
                                ladoM,
                                espM,
                                comp,
                                densidade
                            )
                        }

                        "Tubo Retangular" -> {
                            val baseM = larg / 1000.0
                            val alturaM = espMm / 1000.0
                            CalculadoraPeso.calcularTuboRetangular(
                                baseM,
                                alturaM,
                                espM,
                                comp,
                                densidade
                            )
                        }

                        "Viga U" -> {
                            if (baseMm != null) {
                                val alturaM = larg / 1000.0
                                val baseM = baseMm / 1000.0
                                CalculadoraPeso.calcularVigaU(
                                    alturaM,
                                    baseM,
                                    espM,
                                    comp,
                                    densidade
                                )
                            } else 0.0
                        }

                        "Viga U Enrijecida" -> {
                            if (baseMm != null && retornoMm != null) {
                                val alturaM = larg / 1000.0
                                val baseM = baseMm / 1000.0
                                val retornoM = retornoMm / 1000.0
                                CalculadoraPeso.calcularVigaUEnrijecida(
                                    alturaM,
                                    baseM,
                                    retornoM,
                                    espM,
                                    comp,
                                    densidade
                                )
                            } else 0.0
                        }

                        else -> 0.0
                    }

                    resultado = if (preco != null) {
                        val valorTotal = peso * preco
                        "Peso: %.2f kg\nValor estimado: R$ %.2f".format(peso, valorTotal)
                    } else {
                        "Peso: %.2f kg".format(peso)
                    }

                } else {
                    resultado = "Preencha todos os campos corretamente"
                }
            }
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(resultado)
    }
}
