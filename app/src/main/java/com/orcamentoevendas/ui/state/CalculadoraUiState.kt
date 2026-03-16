package com.orcamentoevendas.ui.state

import com.orcamentoevendas.data.local.entity.OrcamentoEntity

data class CalculadoraUiState(
    val tipoPeca: String = "Chapa",
    val material: String = "Aço",
    val comprimento: String = "",
    val largura: String = "",
    val espessura: String = "",
    val precoKg: String = "",
    val baseAba: String = "",
    val retorno: String = "",
    val quantidade: String = "1",
    val resultadoAtual: Double? = null,
    val mensagemErro: String? = null,
    val historico: List<OrcamentoEntity> = emptyList()
)
