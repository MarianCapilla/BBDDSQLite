package com.example.bbddsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bbddsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var amigosDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        amigosDBHelper = miSQLiteHelper(this)

        binding.btGuardar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank()) {
                amigosDBHelper.anyadirDato(binding.etNombre.text.toString(),
                    binding.etEmail.text.toString())
                binding.etNombre.text.clear()
                binding.etEmail.text.clear()
                Toast.makeText(this, "Guardado",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "No se ha podido guardar",
                    Toast.LENGTH_LONG).show()
            }
        }
        binding.btConsultar.setOnClickListener {
            binding.tvConsulta.text = ""
            val cursor = amigosDBHelper.mostrarDatos()
            if (cursor!!.moveToFirst()) {
                do {
                    binding.tvConsulta.append(
                        cursor.getInt(0).toString() + ": ")
                    binding.tvConsulta.append(
                        cursor.getString(1).toString()+ ", ")
                    binding.tvConsulta.append(
                        cursor.getString(2).toString() + "\n")
                } while (cursor.moveToNext())
            }

        }

        binding.btIrABorrar.setOnClickListener{
            val intent = Intent(this, BorrarDatos::class.java)
            startActivity(intent)
        }

        binding.btIrAModificar.setOnClickListener{
            val intent = Intent(this, ModificarDatos::class.java)
            startActivity(intent)
        }





    }
}
