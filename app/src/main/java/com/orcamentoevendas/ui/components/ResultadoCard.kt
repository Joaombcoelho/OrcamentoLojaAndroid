package com.orcamentoevendas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ResultadoCard(
    peso: Double,
    valorTotal: Double
) {

    val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Peso total: %.3f kg".format(peso),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Valor total: ${formatoMoeda.format(valorTotal)}",
                style = MaterialTheme.typography.titleMedium
            )

        }
    }
}