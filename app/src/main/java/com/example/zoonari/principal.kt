package com.example.zoonari

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // --- CÓDIGO PARA HACER FUNCIONAR EL BOTÓN ---

        // 1. Encontrar el botón "play" en el layout usando su ID.
        val botonPlay = findViewById<ImageButton>(R.id.btn_play)

        // 2. Asignar una acción para cuando el usuario haga clic.
        botonPlay.setOnClickListener {

            val intent = Intent(this, InicioSesionActivity::class.java)

            // 4. Ejecutar la orden para abrir la nueva pantalla.
            startActivity(intent)
        }
    }
}


