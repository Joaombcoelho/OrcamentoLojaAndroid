package com.orcamentoevendas.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CalculadoraViewModel : ViewModel() {

    fun calcularESalvar(
        peca: String,
        comprimento: Double,
        pesoTotal: Double,
        valorTotal: Double
    ) {

        viewModelScope.launch {

            println(
                "Salvar histórico -> " +
                        "Peça: $peca " +
                        "Comprimento: $comprimento " +
                        "Peso: $pesoTotal " +
                        "Valor: $valorTotal"
            )

        }
    }
}
