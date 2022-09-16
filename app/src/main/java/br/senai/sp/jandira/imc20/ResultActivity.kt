package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc20.databinding.ActivityResultBinding
import br.senai.sp.jandira.imc20.utils.getBmi
import br.senai.sp.jandira.imc20.utils.getStatusBmi

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dados = getSharedPreferences("Dados", MODE_PRIVATE)

        //Recuperar os valores que estão na INTENT
        val peso = intent.getIntExtra("peso", 0)
        val altura = intent.getDoubleExtra("altura", 0.0)

        val result = getBmi(peso, altura)
        val showStatus = getStatusBmi(result, this)

        binding.textViewResult.text = "${String.format("%.2f", result)}"
        binding.textViewStatus.text = "$showStatus"

        supportActionBar!!.hide()
    }
}