package com.orcamentoevendas.domain.model

data class Cliente(
    val id: Long = 0,
    val nome: String,
    val empresa: String?,
    val telefone: String?,
    val email: String?
)