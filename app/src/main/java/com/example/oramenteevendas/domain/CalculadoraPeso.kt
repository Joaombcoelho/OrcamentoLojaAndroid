package com.example.oramenteevendas.domain

object CalculadoraPeso {

    fun calcularChapa(
        comprimento: Double,
        largura: Double,
        espessura: Double,
        densidade: Double
    ): Double {
        return comprimento * largura * espessura * densidade
    }

    fun calcularTuboQuadrado(

        ladoExterno: Double,
        espessura: Double,
        comprimento: Double,
        densidade: Double
    ): Double {
        val ladoInterno = ladoExterno - (2 * espessura)
        val areaExterna = ladoExterno * ladoExterno
        val areaInterna = ladoInterno * ladoInterno
        val area = areaExterna - areaInterna
        return area * comprimento * densidade
    }

    fun calcularTuboRedondo(
        raioExterno: Double,
        espessura: Double,
        comprimento: Double,
        densidade: Double
    ): Double {
        val raioInterno = raioExterno - espessura
        val area = Math.PI *
                (raioExterno * raioExterno - raioInterno * raioInterno)
        return area * comprimento * densidade
    }

    fun calcularTuboRetangular(
        base: Double,
        altura: Double,
        espessura: Double,
        comprimento: Double,
        densidade: Double
    ): Double {

        val baseInterna = base - (2 * espessura)
        val alturaInterna = altura - (2 * espessura)

        if (baseInterna <= 0 || alturaInterna <= 0) return 0.0

        val areaExterna = base * altura
        val areaInterna = baseInterna * alturaInterna

        val areaReal = areaExterna - areaInterna

        val volume = areaReal * comprimento

        return volume * densidade
    }


    fun calcularVigaU(
        altura: Double,
        base: Double,
        espessura: Double,
        comprimento: Double,
        densidade: Double
    ): Double {

        val area = (altura * espessura) +
                (2 * base * espessura)

        return area * comprimento * densidade
    }

    fun calcularVigaUEnrijecida(
        altura: Double,
        base: Double,
        retorno: Double,
        espessura: Double,
        comprimento: Double,
        densidade: Double
    ): Double {

        val area =
            (altura * espessura) +              // alma
                    (2 * base * espessura) +            // abas
                    (2 * retorno * espessura)           // retornos

        return area * comprimento * densidade
    }

}