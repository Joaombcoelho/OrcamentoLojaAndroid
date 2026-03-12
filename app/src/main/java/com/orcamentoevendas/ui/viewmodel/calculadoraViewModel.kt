package com.orcamentoevendas.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orcamentoevendas.data.local.dao.OrcamentoDao
import com.orcamentoevendas.data.local.entity.OrcamentoEntity
import com.orcamentoevendas.ui.state.CalculadoraUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculadoraViewModel @Inject constructor(
    private val dao: OrcamentoDao
) : ViewModel() {

    private val _resultadoAtual = MutableStateFlow<Double?>(null)

    private val historicoFlow: Flow<List<OrcamentoEntity>> =
        dao.listarHistorico()

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

        viewModelScope.launch {

            val entity = OrcamentoEntity(
                tipoPeca = "Peça",
                dimensoes = "N/A",
                comprimento = 0.0,
                pesoTotal = pesoTotal,
                valorTotal = valorTotal,
                data = System.currentTimeMillis()
            )

            dao.inserir(entity)
        }
    }

    fun limparHistorico() {
        viewModelScope.launch {
            dao.limparHistorico()
        }
    }
}