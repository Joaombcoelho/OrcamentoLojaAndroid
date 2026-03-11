package com.orcamentoevendas.ui.state

import com.orcamentoevendas.data.local.entity.OrcamentoEntity

data class CalculadoraUiState(
    val resultadoAtual: Double? = null,
    val historico: List<OrcamentoEntity> = emptyList()
)