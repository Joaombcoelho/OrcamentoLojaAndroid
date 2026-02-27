package com.example.oramenteevendas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import java.text.NumberFormat
import java.util.Locale
import com.example.oramenteevendas.domain.ResultadoCalculo

@Composable
fun ResultadoCard(resultado: ResultadoCalculo?) {

    if (resultado == null) return

    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text("Peso unitário: %.2f kg".format(resultado.pesoUnitario))
            Text("Peso total: %.2f kg".format(resultado.pesoTotal))

            resultado.valorTotal?.let {
                Text("Valor total: ${formatoMoeda.format(it)}")
            }
        }
    }
}