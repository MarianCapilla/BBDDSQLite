package com.example.bbddsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper (context: Context) : SQLiteOpenHelper(
    context, "amigos.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion =
                "CREATE TABLE amigos " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",nombre TEXT" +
                ",email TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS amigos"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun anyadirDato(nombre: String, email: String) {
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase
        /*Abrimos la base de datos en modo escritura*/
        db.insert("amigos", null, datos)
        db.close()
    }

    fun mostrarDatos(): Cursor? {
        val db : SQLiteDatabase = this.readableDatabase
        /*Abrimos la base de datos en modo lectura*/
        val cursor = db.rawQuery(
            "SELECT * FROM amigos",
            null)
        return cursor
    }

    fun borrarDato(id: Int) : Int {
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val borrados = db.delete("amigos", "id = ?", args)
        // db.execSQL("DELETE FROM amigos WHERE id = ?", args)
        db.close()
        return borrados
    }

    fun modificarDato(id: Int, nombre: String, email: String) {
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase
        db.update("amigos", datos, "id = ?", args)
       // db.execSQL("UPATE amigos SET nombre = ?, email = ?, WHERE id = ?",nombre,email, args)
        db.close()
    }


}