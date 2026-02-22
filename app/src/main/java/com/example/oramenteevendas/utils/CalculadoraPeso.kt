package com.example.oramenteevendas.utils

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
        baseExterna: Double,
        alturaExterna: Double,
        espessura: Double,
        comprimento: Double,
        densidade: Double
    ): Double {

        val baseInterna = baseExterna - (2 * espessura)
        val alturaInterna = alturaExterna - (2 * espessura)

        val areaExterna = baseExterna * alturaExterna
        val areaInterna = baseInterna * alturaInterna

        val area = areaExterna - areaInterna

        return area * comprimento * densidade
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
