package com.orcamentoevendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.orcamentoevendas.ui.screens.CalculadoraScreen
import com.orcamentoevendas.ui.theme.OrcamenteEVendasTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.orcamentoevendas.data.local.AppDatabaseProvider
import com.orcamentoevendas.ui.viewmodel.CalculadoraViewModel
import com.orcamentoevendas.ui.screens.TelaPrincipal







class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current
            val database = remember {
                AppDatabaseProvider.getDatabase(context)
            }

            val viewModel = remember {
                CalculadoraViewModel(database.resultadoDao())
            }

            setContent {
                TelaPrincipal()
            }
        }
        }
    }
