package com.example.oramenteevendas.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.oramenteevendas.domain.ResultadoCalculo
import com.example.oramenteevendas.ui.state.CalculadoraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculadoraViewModel(
    private val dao: ResultadoDao
) : ViewModel() {

    val historico = dao.listar()

    fun salvarResultado(
        comprimento: Double,
        largura: Double,
        valorUnitario: Double,
        resultado: Double
    ) {
        viewModelScope.launch {
            dao.inserir(
                ResultadoEntity(
                    comprimento = comprimento,
                    largura = largura,
                    valorUnitario = valorUnitario,
                    resultado = resultado
                )
            )
        }
    }
}