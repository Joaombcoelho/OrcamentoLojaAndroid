package com.orcamentoevendas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orcamentoevendas.data.local.dao.HistoricoDao
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel

class CalculadoraViewModelFactory(
    private val dao: HistoricoDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CalculadoraViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return CalculadoraViewModel(dao) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}