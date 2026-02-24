package com.example.oramenteevendas.utils


    import java.text.NumberFormat
    import java.util.Locale

    fun Double.format2(): String {
        val formato = NumberFormat.getNumberInstance(Locale("pt", "BR"))
        formato.minimumFractionDigits = 2
        formato.maximumFractionDigits = 2
        return formato.format(this)
    }

    fun Double.formatCurrency(): String {
        val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return formato.format(this)
    }
