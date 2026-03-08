package com.orcamentoevendas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orcamentoevendas.data.local.dao.HistoricoDao
import com.orcamentoevendas.data.local.entity.ResultadoEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.orcamentoevendas.ui.state.CalculadoraUiState


class CalculadoraViewModel(
    private val dao: HistoricoDao
) : ViewModel() {

    private val _resultadoAtual = MutableStateFlow<Double?>(null)

    private val historicoFlow = dao.listar()

    val uiState: StateFlow<CalculadoraUiState> =
        combine(_resultadoAtual, historicoFlow) { resultado, historico ->
            CalculadoraUiState(
                resultadoAtual = resultado,
                historico = historico
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            CalculadoraUiState()
        )

    fun calcularESalvar(
        pesoTotal: Double,
        valorTotal: Double
    ) {

        _resultadoAtual.value = pesoTotal

        viewModelScope.launch {

            val dataFormatada = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

            val entity = ResultadoEntity(
                valorTotal = valorTotal,
                pesoTotal = pesoTotal,
                data = dataFormatada
            )

            dao.inserir(entity)
        }
    }

    fun limparHistorico() {
        viewModelScope.launch {
            dao.limpar()
        }
    }
}