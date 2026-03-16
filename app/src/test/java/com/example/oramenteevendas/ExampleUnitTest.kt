package com.orcamentoevendas

import com.orcamentoevendas.domain.CalculadoraPeso
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun calculadoraPeso_smokeTest() {
        val resultado = CalculadoraPeso.calcularChapa(1.0, 1.0, 0.001, 7850.0)
        assertTrue(resultado > 0)
    }
}
