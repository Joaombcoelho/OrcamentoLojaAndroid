package com.orcamentoevendas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orcamentoevendas.data.local.entity.OrcamentoEntity
import com.orcamentoevendas.data.repository.OrcamentoRepository
import com.orcamentoevendas.domain.CalculadoraPeso
import com.orcamentoevendas.ui.state.CalculadoraUiState
import com.orcamentoevendas.utils.Densidades
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalculadoraViewModel @Inject constructor(
    private val repository: OrcamentoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculadoraUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.listarHistorico().collect { historico ->
                _uiState.update { it.copy(historico = historico) }
            }
        }
    }

    fun atualizarTipoPeca(tipo: String) {
        _uiState.update {
            it.copy(
                tipoPeca = tipo,
                largura = "",
                espessura = "",
                baseAba = "",
                retorno = "",
                resultadoAtual = null
            )
        }
    }

    fun atualizarComprimento(valor: String) = _uiState.update { it.copy(comprimento = sanitizarNumero(valor)) }
    fun atualizarLargura(valor: String) = _uiState.update { it.copy(largura = sanitizarNumero(valor)) }
    fun atualizarEspessura(valor: String) = _uiState.update { it.copy(espessura = sanitizarNumero(valor)) }
    fun atualizarPrecoKg(valor: String) = _uiState.update { it.copy(precoKg = sanitizarNumero(valor)) }
    fun atualizarBaseAba(valor: String) = _uiState.update { it.copy(baseAba = sanitizarNumero(valor)) }
    fun atualizarRetorno(valor: String) = _uiState.update { it.copy(retorno = sanitizarNumero(valor)) }
    fun atualizarQuantidade(valor: String) = _uiState.update { it.copy(quantidade = sanitizarNumero(valor)) }

    fun calcularResultado() {
        val state = _uiState.value

        val comp = state.comprimento.toDoubleOrNull() ?: return
        val larg = state.largura.toDoubleOrNull() ?: return
        val espMm = state.espessura.toDoubleOrNull() ?: return
        val baseMm = state.baseAba.toDoubleOrNull()
        val retornoMm = state.retorno.toDoubleOrNull()
        val qtd = state.quantidade.toIntOrNull() ?: 1

        val espM = espMm / 1000.0
        val densidade = Densidades.ACO

        val peso = when (state.tipoPeca) {
            "Chapa" -> CalculadoraPeso.calcularChapa(comp, larg, espM, densidade)
            "Tubo Quadrado" -> {
                val ladoM = larg / 1000.0
                CalculadoraPeso.calcularTuboQuadrado(ladoM, espM, comp, densidade)
            }

            "Tubo Retangular" -> {
                if (baseMm == null) return
                val baseM = larg / 1000.0
                val alturaM = baseMm / 1000.0
                CalculadoraPeso.calcularTuboRetangular(baseM, alturaM, espM, comp, densidade)
            }

            "Viga U" -> {
                if (baseMm == null) return
                val alturaM = larg / 1000.0
                val baseM = baseMm / 1000.0
                CalculadoraPeso.calcularVigaU(alturaM, baseM, espM, comp, densidade)
            }

            "Viga U Enrijecida" -> {
                if (baseMm == null || retornoMm == null) return
                val alturaM = larg / 1000.0
                val baseM = baseMm / 1000.0
                val retornoM = retornoMm / 1000.0
                CalculadoraPeso.calcularVigaUEnrijecida(
                    alturaM,
                    baseM,
                    retornoM,
                    espM,
                    comp,
                    densidade
                )
            }

            "Tubo Redondo" -> {
                val diametroM = larg / 1000.0
                CalculadoraPeso.calcularTuboRedondo(diametroM, espM, comp, densidade)
            }

            else -> 0.0
        }

        _uiState.update { it.copy(resultadoAtual = peso * qtd) }
    }

    fun salvarResultadoAtual() {
        val state = _uiState.value
        val pesoTotal = state.resultadoAtual ?: return
        val comprimento = state.comprimento.toDoubleOrNull() ?: return
        val preco = state.precoKg.replace(",", ".").toDoubleOrNull() ?: 0.0
        val valorTotal = pesoTotal * preco

        viewModelScope.launch {
            repository.inserir(
                OrcamentoEntity(
                    tipoPeca = state.tipoPeca,
                    comprimento = comprimento,
                    dimensoes = montarDimensoes(state),

                    private val _uiState = MutableStateFlow(CalculadoraUiState())
                val uiState = _uiState.asStateFlow()

            init {
                viewModelScope.launch {
                    repository.listarHistorico().collect { historico ->
                        _uiState.update { it.copy(historico = historico) }
                    }
                }
            }

            fun atualizarTipoPeca(tipo: String) {
                _uiState.update {
                    it.copy(
                        tipoPeca = tipo,
                        largura = "",
                        espessura = "",
                        baseAba = "",
                        retorno = "",
                        resultadoAtual = null
                    )
                }
            }

            fun atualizarComprimento(valor: String) = _uiState.update { it.copy(comprimento = sanitizarNumero(valor)) }
            fun atualizarLargura(valor: String) = _uiState.update { it.copy(largura = sanitizarNumero(valor)) }
            fun atualizarEspessura(valor: String) = _uiState.update { it.copy(espessura = sanitizarNumero(valor)) }
            fun atualizarPrecoKg(valor: String) = _uiState.update { it.copy(precoKg = sanitizarNumero(valor)) }
            fun atualizarBaseAba(valor: String) = _uiState.update { it.copy(baseAba = sanitizarNumero(valor)) }
            fun atualizarRetorno(valor: String) = _uiState.update { it.copy(retorno = sanitizarNumero(valor)) }
            fun atualizarQuantidade(valor: String) = _uiState.update { it.copy(quantidade = sanitizarNumero(valor)) }

            fun calcularResultado() {
                val state = _uiState.value

                val comp = state.comprimento.toDoubleOrNull() ?: return
                val larg = state.largura.toDoubleOrNull() ?: return
                val espMm = state.espessura.toDoubleOrNull() ?: return
                val baseMm = state.baseAba.toDoubleOrNull()
                val retornoMm = state.retorno.toDoubleOrNull()
                val qtd = state.quantidade.toIntOrNull() ?: 1

                val espM = espMm / 1000.0
                val densidade = Densidades.ACO

                val peso = when (state.tipoPeca) {
                    "Chapa" -> CalculadoraPeso.calcularChapa(comp, larg, espM, densidade)
                    "Tubo Quadrado" -> {
                        val ladoM = larg / 1000.0
                        CalculadoraPeso.calcularTuboQuadrado(ladoM, espM, comp, densidade)
                    }

                    "Tubo Retangular" -> {
                        if (baseMm == null) return
                        val baseM = larg / 1000.0
                        val alturaM = baseMm / 1000.0
                        CalculadoraPeso.calcularTuboRetangular(baseM, alturaM, espM, comp, densidade)
                    }

                    "Viga U" -> {
                        if (baseMm == null) return
                        val alturaM = larg / 1000.0
                        val baseM = baseMm / 1000.0
                        CalculadoraPeso.calcularVigaU(alturaM, baseM, espM, comp, densidade)
                    }

                    "Viga U Enrijecida" -> {
                        if (baseMm == null || retornoMm == null) return
                        val alturaM = larg / 1000.0
                        val baseM = baseMm / 1000.0
                        val retornoM = retornoMm / 1000.0
                        CalculadoraPeso.calcularVigaUEnrijecida(
                            alturaM,
                            baseM,
                            retornoM,
                            espM,
                            comp,
                            densidade
                        )
                    }

                    "Tubo Redondo" -> {
                        val diametroM = larg / 1000.0
                        CalculadoraPeso.calcularTuboRedondo(diametroM, espM, comp, densidade)
                    }

                    else -> 0.0
                }

                _uiState.update { it.copy(resultadoAtual = peso * qtd) }
            }

            fun salvarResultadoAtual() {
                val state = _uiState.value
                val pesoTotal = state.resultadoAtual ?: return
                val comprimento = state.comprimento.toDoubleOrNull() ?: return
                val preco = state.precoKg.replace(",", ".").toDoubleOrNull() ?: 0.0
                val valorTotal = pesoTotal * preco

                viewModelScope.launch {
                    repository.inserir(
                        OrcamentoEntity(
                            tipoPeca = state.tipoPeca,
                            comprimento = comprimento,
                            dimensoes = montarDimensoes(state),

                            private val _uiState = MutableStateFlow(CalculadoraUiState())
                        val uiState = _uiState.asStateFlow()

                    init {
                        viewModelScope.launch {
                            repository.listarHistorico().collect { historico ->
                                _uiState.update { it.copy(historico = historico) }
                            }
                        }
                    }

                    fun atualizarResultado(resultado: Double) {
                        _uiState.update { it.copy(resultadoAtual = resultado) }
                    }

                    fun calcularESalvar(
                        peca: String,
                        comprimento: Double,
                        pesoTotal: Double,
                        valorTotal: Double
                    ) {
                        viewModelScope.launch {
                            repository.inserir(
                                OrcamentoEntity(
                                    tipoPeca = peca,
                                    comprimento = comprimento,
                                    dimensoes = "-",
                                    pesoTotal = pesoTotal,
                                    valorTotal = valorTotal,
                                    data = System.currentTimeMillis()
                                )
                            )
                        }
                    }

                    private fun montarDimensoes(state: CalculadoraUiState): String {
                        return buildString {
                            append("Largura: ${state.largura}")
                            if (state.baseAba.isNotBlank()) append(" | Base/Aba: ${state.baseAba}")
                            if (state.retorno.isNotBlank()) append(" | Retorno: ${state.retorno}")
                            append(" | Espessura: ${state.espessura}")
                        }
                    }

                    private fun sanitizarNumero(valor: String): String {
                        return valor.replace(",", ".").filter { it.isDigit() || it == '.' }
                    }
                }
            }

            private fun sanitizarNumero(valor: String): String {
                return valor.replace(",", ".").filter { it.isDigit() || it == '.' }
            }
        }
    }
}