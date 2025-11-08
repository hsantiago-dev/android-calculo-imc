package br.edu.utfpr.calculaimc.util

import kotlin.math.pow

class ImcUtil {

    companion object {
        fun calcularImc(peso: Double, altura: Double, countryCode: String): Double {
            return if (countryCode.equals("US", ignoreCase = true)) {
                703 * (peso / altura.pow(2.0))
            } else {
                peso / altura.pow(2.0)
            }
        }
    }
}