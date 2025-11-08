package br.edu.utfpr.calculaimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.calculaimc.util.ImcUtil
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso: EditText
    private lateinit var etAltura: EditText
    private lateinit var tvResultado: TextView
    private lateinit var btCalcular: Button
    private lateinit var btLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }

    private fun init() {
        etPeso = findViewById(R.id.etWeight)
        etAltura = findViewById(R.id.etHeight)
        tvResultado = findViewById(R.id.tvResult)
        btCalcular = findViewById(R.id.btCalculate)
        btLimpar = findViewById(R.id.btClean)

        btCalcular.setOnClickListener {
            calcular()
        }

        btLimpar.setOnClickListener {
            limpar()
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText(this, getString(R.string.clean_long_click), Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun calcular() {
        val peso = etPeso.text.toString().toDoubleOrNull()
        val altura = etAltura.text.toString().toDoubleOrNull()

        if (peso == null) {
            etPeso.error = getString(R.string.weight_error)
            return
        } else if (altura == null) {
            etAltura.error = getString(R.string.height_error)
            return
        }

        val locale = Locale.getDefault().language
        val countryCode = Locale.getDefault().country
        val imc = ImcUtil.calcularImc(peso, altura, countryCode)

        val imcFormatado = String.format(Locale.getDefault(), "%.2f", imc)

        if (locale.equals("en", ignoreCase = true)) {
            tvResultado.text = imcFormatado
        } else {
            tvResultado.text = imcFormatado.replace(".", ",")
        }
    }

    private fun limpar() {
        etPeso.text.clear()
        etAltura.text.clear()
        tvResultado.text = getString(R.string.zeros)
    }
}