package com.example.bbddsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bbddsqlite.databinding.ActivityBorrarDatosBinding

class BorrarDatos : AppCompatActivity() {
    lateinit var binding: ActivityBorrarDatosBinding
    lateinit var amigosDBHelper: miSQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        amigosDBHelper = miSQLiteHelper(this)
        binding.btBorrar.setOnClickListener {

            var cantidad = 0

            if (binding.etId.text.isNotBlank()) {
                amigosDBHelper.borrarDato(
                    binding.etId.text.toString().toInt())
                binding.etId.text.clear()
            }
            else {
                Toast.makeText(this,
                    "Datos borrados: $cantidad",
                    Toast.LENGTH_LONG).show()
            }

        }
    }


}