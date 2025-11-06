package com.example.zoonari

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // ===== INICIO DE LA MODIFICACIÓN =====

        // Tiempo que durará la pantalla principal (2000 milisegundos = 2 segundos)
        val TIEMPO_SPLASH: Long = 2000

        // Handler para ejecutar una acción después de un tiempo
        Handler(Looper.getMainLooper()).postDelayed({

            // Creamos un intent para ir a la actividad de inicio de sesión
            val intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)

            // Cerramos esta actividad (PrincipalActivity) para que el usuario no pueda volver a ella
            finish()

        }, TIEMPO_SPLASH)

        // ===== FIN DE LA MODIFICACIÓN =====
    }
}







