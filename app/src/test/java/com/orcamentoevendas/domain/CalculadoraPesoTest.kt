package com.orcamentoevendas.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculadoraPesoTest {

    @Test
    fun calcularChapa_retornaPesoEsperado() {
        val peso = CalculadoraPeso.calcularChapa(
            comprimento = 2.0,
            largura = 1.0,
            espessura = 0.01,
            densidade = 7850.0
        )

        assertEquals(157.0, peso, 0.0001)
    }

    @Test
    fun calcularTuboRetangular_quandoDimensaoInternaInvalida_retornaZero() {
        val peso = CalculadoraPeso.calcularTuboRetangular(
            base = 0.02,
            altura = 0.02,
            espessura = 0.02,
            comprimento = 1.0,
            densidade = 7850.0
        )

        assertEquals(0.0, peso, 0.0)
    }

    @Test
    fun calcularTuboRedondo_retornaPesoEsperado() {
        val peso = CalculadoraPeso.calcularTuboRedondo(
            raioExterno = 0.05,
            espessura = 0.005,
            comprimento = 2.0,
            densidade = 7850.0
        )

        assertEquals(23.4203522483, peso, 0.0001)
    }
}
