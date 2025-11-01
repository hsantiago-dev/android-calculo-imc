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
import java.util.Locale
import kotlin.math.pow

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
        etPeso = findViewById(R.id.peso)
        etAltura = findViewById(R.id.altura)
        tvResultado = findViewById(R.id.resultado)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnClickListener {
            calcular()
        }

        btLimpar.setOnClickListener {
            limpar()
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText(this, "Botão de limpar a tela", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun calcular() {
        val peso = etPeso.text.toString().toDoubleOrNull()
        val altura = etAltura.text.toString().toDoubleOrNull()

        if (peso == null) {
            etPeso.error = "Peso deve ser informado"
            return
        } else if (altura == null) {
            etAltura.error = "Altura deve ser informada"
            return
        }

        val imc = peso / altura.pow(2.0)

        tvResultado.text = String.format(Locale.getDefault(), "%.2f", imc).replace(".", ",")
    }

    private fun limpar() {
        etPeso.text.clear()
        etAltura.text.clear()
        tvResultado.text = "0,0"
    }
}