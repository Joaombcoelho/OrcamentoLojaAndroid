package com.example.oramenteevendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.oramenteevendas.ui.screens.CalculadoraScreen
import com.example.oramenteevendas.ui.theme.OrcamenteEVendasTheme
import com.example.oramenteevendas.ui.navigation.AppNavigation

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

            TelaPrincipal(viewModel)
        }
        }
    }
