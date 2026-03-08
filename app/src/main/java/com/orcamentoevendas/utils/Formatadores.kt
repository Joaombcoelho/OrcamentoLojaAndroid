package com.orcamentoevendas.utils

import java.text.NumberFormat
import java.util.Locale

fun formatarPeso(valor: Double): String {
    return String.format("%.3f", valor)
}

fun formatarMoeda(valor: Double): String {
    val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formato.format(valor)
}