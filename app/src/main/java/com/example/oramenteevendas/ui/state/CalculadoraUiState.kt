package com.example.oramenteevendas.ui.state

import com.example.oramenteevendas.domain.ResultadoCalculo

data class CalculadoraUiState(
    val resultado: ResultadoCalculo? = null,
    val historico: List<ResultadoCalculo> = emptyList()
)