package com.orcamentoevendas.domain.model

data class Orcamento(
    val id: Long = 0,
    val clienteId: Long?,
    val data: Long,
    val pesoTotal: Double,
    val valorTotal: Double
)