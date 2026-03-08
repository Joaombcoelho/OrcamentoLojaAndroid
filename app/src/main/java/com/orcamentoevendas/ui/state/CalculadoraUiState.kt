package com.orcamentoevendas.ui.state

import com.orcamentoevendas.data.local.entity.ResultadoEntity

data class CalculadoraUiState(
    val resultadoAtual: Double? = null,
    val historico: List<ResultadoEntity> = emptyList()
)