package com.example.oramenteevendas.model


data class ResultadoCalculo(
    val pesoUnitario: Double,
    val kgPorMetro: Double,
    val pesoTotal: Double,
    val valorUnitario: Double? = null,
    val valorTotal: Double? = null
)
