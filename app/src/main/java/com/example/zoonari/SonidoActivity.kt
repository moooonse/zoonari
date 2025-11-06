package com.example.zoonari

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SonidoActivity : AppCompatActivity() {

    // 1. DECLARAR solo las variables que vamos a usar
    private lateinit var btnAtras: ImageButton
    private lateinit var btnGrabar: ImageButton
    // Se eliminaron btnPausa y btnDetener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sonido)

        // 2. INICIALIZAR solo las variables que existen
        btnAtras = findViewById(R.id.btn_atras)
        btnGrabar = findViewById(R.id.btn_grabar)
        // Se eliminaron las inicializaciones de btnPausa y btnDetener

        // 3. ASIGNAR las acciones a los botones existentes
        btnAtras.setOnClickListener {
            // Cierra la actividad actual y regresa a la pantalla anterior.
            finish()
        }

        btnGrabar.setOnClickListener {
            // TODO: Iniciar la grabación
        }

        // Se eliminaron los setOnClickListener de btnPausa y btnDetener
    }
}



