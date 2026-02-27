package com.example.oramenteevendas.domain


data class ResultadoCalculo(
    val pesoUnitario: Double,
    val pesoTotal: Double,
    val valorTotal: Double?
)

fun calcular(
    peso: Double,
    comprimento: Double,
    qtd: Int,
    preco: Double?
): ResultadoCalculo {

    val pesoTotal = peso * qtd

    val valorTotal = preco?.let { pesoTotal * it }

    return ResultadoCalculo(
        pesoUnitario = peso,
        pesoTotal = pesoTotal,
        valorTotal = valorTotal
    )
}