package com.orcamentoevendas.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.orcamentoevendas.ui.navigation.AppNavigation
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel

@Composable
fun TelaPrincipal() {
    val viewModel: CalculadoraViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    AppNavigation(
        viewModel = viewModel,
        uiState = uiState
    )
}
