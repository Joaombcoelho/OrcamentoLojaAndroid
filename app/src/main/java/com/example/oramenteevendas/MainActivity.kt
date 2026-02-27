package com.example.oramenteevendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.oramenteevendas.ui.screens.CalculadoraScreen
import com.example.oramenteevendas.ui.theme.OrcamenteEVendasTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            OrcamenteEVendasTheme {
                CalculadoraScreen()
            }
        }
    }
}