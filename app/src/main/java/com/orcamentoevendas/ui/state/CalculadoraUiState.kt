package com.orcamentoevendas.ui.state

import com.orcamentoevendas.data.local.entity.OrcamentoEntity
import com.orcamentoevendas.domain.TipoPeca

data class CalculadoraUiState(
    val tipoPeca: TipoPeca = TipoPeca.CHAPA,
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
