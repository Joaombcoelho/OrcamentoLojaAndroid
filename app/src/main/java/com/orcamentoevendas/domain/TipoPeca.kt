package com.orcamentoevendas.domain

enum class TipoPeca(val label: String) {
    CHAPA("Chapa"),
    TUBO_QUADRADO("Tubo Quadrado"),
    TUBO_RETANGULAR("Tubo Retangular"),
    VIGA_U("Viga U"),
    VIGA_U_ENRIJECIDA("Viga U Enrijecida"),
    TUBO_REDONDO("Tubo Redondo");

    companion object {
        fun fromLabel(label: String): TipoPeca {
            return entries.firstOrNull { it.label == label } ?: CHAPA
        }
    }
}
