package com.example.loginypassword

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var user:EditText
    private lateinit var cont:EditText
    private lateinit var ingresar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        user = findViewById(R.id.usuario)
        cont = findViewById(R.id.contraseña)
        ingresar = findViewById(R.id.ingresar)

        var i = 1

        ingresar.setOnClickListener {
            val kuser = user.getText().toString().trim()
            val contra = cont.getText().toString().trim()

            if (i == 3) {
                finish()
            }
            if (kuser.isEmpty() && contra.isEmpty()) {
                Toast.makeText(this, "Ingresar los datos", Toast.LENGTH_SHORT).show()

            } else {
                if (kuser == "Admi" && contra == "admi123") {
                    val intent = Intent(this, Ventana2::class.java)
                    intent.putExtra("Usuario", user.text.toString())
                    user.text.clear()
                    cont.text.clear()
                    startActivity(intent)
                    i = 1
                } else {
                    Toast.makeText(this,"Nombre de usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show()
                    i++
                }
            }
        }
    }
}
