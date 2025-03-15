package com.example.loginypassword

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var ed1: EditText
    private lateinit var ed2: EditText
    private lateinit var ingresar: Button
    private lateinit var registrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ed1 = findViewById(R.id.usuario)
        ed2 = findViewById(R.id.contraseña)
        ingresar = findViewById(R.id.ingresar)
        registrar = findViewById(R.id.registrar)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        registrar.setOnClickListener { registar(it) }
        ingresar.setOnClickListener { ingresar(it) }

    }

    fun registar(view: View) {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val user = ed1.text.toString()
        val cont = ed2.text.toString()
        val registro = ContentValues().apply {
            put("user", user)
            put("cont", cont)
        }
        bd.insert("usuario", null, registro)
        bd.close()
        Toast.makeText(this, "Datos del usuario cargados con exito", Toast.LENGTH_SHORT).show()
        ed1.text.clear()
        ed2.text.clear()
    }
    
    var i = 1

    fun ingresar(view: View) {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val kuser = ed1.text.toString()
        val contra = ed2.text.toString()

        if (i == 3) {
            finish()
        }
        if (kuser.isEmpty() && contra.isEmpty()) {
            Toast.makeText(this, "Ingresar los datos", Toast.LENGTH_SHORT).show()

        } else {
            val cursor = bd.rawQuery(
                "SELECT * FROM usuario WHERE usuario = ? AND cont = ?",
                arrayOf(kuser, contra)
            )
            if (cursor.moveToFirst()) {
                // Usuario y contraseña correctos
                val intent = Intent(this, Ventana2::class.java)
                intent.putExtra("Usuario", kuser)
                ed1.text.clear()
                ed2.text.clear()
                startActivity(intent)
                i = 1
            } else {
                Toast.makeText(
                    this,
                    "Nombre de usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
                i++
            }
            cursor.close()
            bd.close()
        }
    }
}
