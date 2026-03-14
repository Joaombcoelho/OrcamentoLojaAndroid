package com.orcamentoevendas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orcamentoevendas.data.local.entity.OrcamentoEntity
import com.orcamentoevendas.data.repository.OrcamentoRepository
import com.orcamentoevendas.ui.state.CalculadoraUiState
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
}
