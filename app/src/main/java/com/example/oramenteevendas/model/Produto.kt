package com.example.oramenteevendas.model

data class Produto(
    val id: String = "",
    val nome: String = "",
    val categoria: String = "",
    val medida: String = "",
    val valorKg: Double = 0.0,
    val ultimaAtualizacao: Long = System.currentTimeMillis()
)
