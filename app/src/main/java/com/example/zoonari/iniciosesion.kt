package com.example.zoonari

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// ASEGÚRATE DE QUE LA CLASE SE LLAME ASÍ
class InicioSesionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciosesion)

        val botonLogin = findViewById<Button>(R.id.btn_login)

        botonLogin.setOnClickListener {
            // Cuando el usuario inicie sesión, lo llevamos a la segunda pantalla.
            val intent = Intent(this, SegundaPantallaActivity::class.java)
            startActivity(intent)
        }

        // Aquí puedes añadir la lógica para los botones de "Registrarse" y "Olvidé contraseña"
    }
}
