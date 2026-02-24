package com.example.oramenteevendas.model


data class ResultadoCalculo(
    val pesoUnitario: Double,
    val kgPorMetro: Double,
    val pesoTotal: Double,
    val valorUnitario: Double? = null,
    val valorTotal: Double? = null
)

fun calcular(
    peso: Double,
    comprimento: Double,
    qtd: Int,
    preco: Double?
): ResultadoCalculo {

    val kgPorMetro = if (comprimento != 0.0) {
        peso / comprimento
    } else 0.0

    val pesoTotal = peso * qtd

    val valorUnitario = preco?.let { peso * it }
    val valorTotal = preco?.let { pesoTotal * it }

    return ResultadoCalculo(
        pesoUnitario = peso,
        kgPorMetro = kgPorMetro,
        pesoTotal = pesoTotal,
        valorUnitario = valorUnitario,
        valorTotal = valorTotal
    )
}