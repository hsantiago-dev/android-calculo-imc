package br.edu.utfpr.calculaimc

import br.edu.utfpr.calculaimc.util.ImcUtil
import org.junit.Test

import org.junit.Assert.*

class MainUnitTest {
    @Test
    fun imc_is_correct() {
        assertTrue(
            "O valor do IMC está correto?",
            ImcUtil.calcularImc(90.0, 1.90, "BR") in 24.93 .. 24.94
        )
    }
}