package com.orcamentoevendas.domain.model

data class ItemOrcamento(
    val id: Long = 0,
    val orcamentoId: Long,
    val descricao: String,
    val material: String,
    val quantidade: Int,
    val peso: Double,
    val valor: Double
)