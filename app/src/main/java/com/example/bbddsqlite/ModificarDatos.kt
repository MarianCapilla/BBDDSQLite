package com.example.bbddsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bbddsqlite.databinding.ActivityBorrarDatosBinding
import com.example.bbddsqlite.databinding.ActivityMainBinding
import com.example.bbddsqlite.databinding.ActivityModificarDatosBinding

class ModificarDatos : AppCompatActivity() {
    lateinit var binding: ActivityModificarDatosBinding
    lateinit var amigosDBHelper: miSQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        amigosDBHelper = miSQLiteHelper(this)
        binding.btModificar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank() &&
                binding.etId.text.isNotBlank()) {
                amigosDBHelper.modificarDato(
                    binding.etId.text.toString().toInt(),
                    binding.etNombre.text.toString(),
                    binding.etEmail.text.toString())
                binding.etNombre.text.clear()
                binding.etEmail.text.clear()
                binding.etId.text.clear()
                Toast.makeText(this, "Modificado",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this,
                    "Los campos no deben estar vacíos",
                    Toast.LENGTH_LONG).show()
            }
        }

    }
}