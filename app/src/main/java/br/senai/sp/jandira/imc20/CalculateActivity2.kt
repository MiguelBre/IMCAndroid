package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc20.databinding.ActivityCalculate2Binding

class CalculateActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityCalculate2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculate2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar!!.hide()

        loadProfile()

        binding.buttonCalculate.setOnClickListener {
                bmiCalculate()
        }
    }

    private fun bmiCalculate() {
        val openResult = Intent(this, ResultActivity::class.java)
        val dados = getSharedPreferences("Dados", MODE_PRIVATE)

        //Enviar os dados de uma activity para outra
        if(binding.editTextWeight3.text.isNotEmpty() && binding.editTextHeight3.text.isNotEmpty()) {
            openResult.putExtra("peso", binding.editTextWeight3.text.toString().toInt())
            openResult.putExtra("altura", binding.editTextHeight3.text.toString().toDouble())
        } else if (binding.editTextWeight3.text.isEmpty() && binding.editTextHeight3.text.isNotEmpty()){
            openResult.putExtra("peso", dados.getInt("weight", 0))
            openResult.putExtra("altura", binding.editTextHeight3.text.toString().toDouble())
        } else if (binding.editTextWeight3.text.isNotEmpty() && binding.editTextHeight3.text.isEmpty()){
            openResult.putExtra("peso", binding.editTextWeight3.text.toString().toInt())
            openResult.putExtra("altura", dados.getFloat("height", 0.0f).toDouble())
        } else {
            openResult.putExtra("peso", dados.getInt("weight", 0))
            openResult.putExtra("altura", dados.getFloat("height", 0.0f).toDouble())
        }
        startActivity(openResult)
    }

    private fun loadProfile() {
        //Abrir o arquivo Shared Preferences
        val dados = getSharedPreferences("Dados", MODE_PRIVATE)

        binding.textViewUsername2.text = dados.getString("nome", "")
        binding.textViewEmail2.text = dados.getString("email", "")
        binding.textViewWeight.text = "${resources.getText(R.string.register_weight)}: ${dados.getInt("weight", 0)} Kg"
        binding.textViewHeight.text = "${resources.getText(R.string.register_height)}: ${dados.getFloat("height", 0.0F)} m"

    }


}