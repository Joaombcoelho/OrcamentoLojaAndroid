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
                resultadoAtual = null,
                mensagemErro = null
            )
        }
    }

    fun atualizarMaterial(material: String) = _uiState.update { it.copy(material = material, mensagemErro = null) }
    fun atualizarComprimento(valor: String) = _uiState.update { it.copy(comprimento = sanitizarNumero(valor), mensagemErro = null) }
    fun atualizarLargura(valor: String) = _uiState.update { it.copy(largura = sanitizarNumero(valor), mensagemErro = null) }
    fun atualizarEspessura(valor: String) = _uiState.update { it.copy(espessura = sanitizarNumero(valor), mensagemErro = null) }
    fun atualizarPrecoKg(valor: String) = _uiState.update { it.copy(precoKg = sanitizarNumero(valor), mensagemErro = null) }
    fun atualizarBaseAba(valor: String) = _uiState.update { it.copy(baseAba = sanitizarNumero(valor), mensagemErro = null) }
    fun atualizarRetorno(valor: String) = _uiState.update { it.copy(retorno = sanitizarNumero(valor), mensagemErro = null) }
    fun atualizarQuantidade(valor: String) = _uiState.update { it.copy(quantidade = sanitizarNumero(valor), mensagemErro = null) }

    fun calcularResultado() {
        val state = _uiState.value

        val comp = state.comprimento.toDoubleOrNull()
            ?: return atualizarErro("Informe um comprimento válido (m).")
        if (comp <= 0) return atualizarErro("Informe um comprimento válido (m).")

        val larg = state.largura.toDoubleOrNull()
            ?: return atualizarErro("Informe uma medida de largura/base/diâmetro válida.")
        if (larg <= 0) return atualizarErro("Informe uma medida de largura/base/diâmetro válida.")

        val espMm = state.espessura.toDoubleOrNull()
            ?: return atualizarErro("Informe uma espessura válida (mm).")
        if (espMm <= 0) return atualizarErro("Informe uma espessura válida (mm).")

        val qtd = state.quantidade.toIntOrNull() ?: 1
        if (qtd <= 0) return atualizarErro("A quantidade deve ser maior que zero.")

        val baseMm = state.baseAba.toDoubleOrNull()
        val retornoMm = state.retorno.toDoubleOrNull()

        when {
            state.tipoPeca == "Tubo Retangular" && (baseMm == null || baseMm <= 0) -> {
                return atualizarErro("Informe a altura do Tubo Retangular.")
            }

            state.tipoPeca == "Viga U" && (baseMm == null || baseMm <= 0) -> {
                return atualizarErro("Informe a base da aba da Viga U.")
            }

            state.tipoPeca == "Viga U Enrijecida" && (baseMm == null || baseMm <= 0) -> {
                return atualizarErro("Informe a base da aba da Viga U Enrijecida.")
            }

            state.tipoPeca == "Viga U Enrijecida" && (retornoMm == null || retornoMm <= 0) -> {
                return atualizarErro("Informe o retorno da Viga U Enrijecida.")
            }
        }

        val espM = espMm / 1000.0
        val densidade = densidadePorMaterial(state.material)

        val peso = when (state.tipoPeca) {
            "Chapa" -> CalculadoraPeso.calcularChapa(comp, larg, espM, densidade)
            "Tubo Quadrado" -> {
                val ladoM = larg / 1000.0
                CalculadoraPeso.calcularTuboQuadrado(ladoM, espM, comp, densidade)
            }

            "Tubo Retangular" -> {
                val baseM = larg / 1000.0
                val alturaM = baseMm!! / 1000.0
                CalculadoraPeso.calcularTuboRetangular(baseM, alturaM, espM, comp, densidade)
            }

            "Viga U" -> {
                val alturaM = larg / 1000.0
                val baseM = baseMm!! / 1000.0
                CalculadoraPeso.calcularVigaU(alturaM, baseM, espM, comp, densidade)
            }

            "Viga U Enrijecida" -> {
                val alturaM = larg / 1000.0
                val baseM = baseMm!! / 1000.0
                val retornoM = retornoMm!! / 1000.0
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

        _uiState.update { it.copy(resultadoAtual = peso * qtd, mensagemErro = null) }
    }

    fun salvarResultadoAtual() {
        val state = _uiState.value
        val pesoTotal = state.resultadoAtual ?: return atualizarErro("Calcule o resultado antes de salvar.")
        val comprimento = state.comprimento.toDoubleOrNull() ?: return atualizarErro("Comprimento inválido.")
        val preco = state.precoKg.replace(",", ".").toDoubleOrNull() ?: 0.0
        val valorTotal = pesoTotal * preco

        viewModelScope.launch {
            repository.inserir(
                OrcamentoEntity(
                    tipoPeca = state.tipoPeca,
                    comprimento = comprimento,
                    dimensoes = montarDimensoes(state),
                    pesoTotal = pesoTotal,
                    valorTotal = valorTotal,
                    data = System.currentTimeMillis()
                )
            )
        }
    }

    private fun montarDimensoes(state: CalculadoraUiState): String {
        return buildString {
            append("Material: ${state.material}")
            append(" | Largura: ${state.largura}")
            if (state.baseAba.isNotBlank()) append(" | Base/Aba: ${state.baseAba}")
            if (state.retorno.isNotBlank()) append(" | Retorno: ${state.retorno}")
            append(" | Espessura: ${state.espessura}")
        }
    }

    private fun densidadePorMaterial(material: String): Double {
        return when (material) {
            "Inox" -> Densidades.INOX
            "Alumínio" -> Densidades.ALUMINIO
            else -> Densidades.ACO
        }
    }

    private fun atualizarErro(mensagem: String) {
        _uiState.update { it.copy(mensagemErro = mensagem) }
    }

    private fun sanitizarNumero(valor: String): String {
        return valor.replace(",", ".").filter { it.isDigit() || it == '.' }
    }
}
