package com.example.zoonari

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SegundaPantallaActivity : AppCompatActivity() {

    // --- CÓDIGO PARA PERMISOS Y CÁMARA (Sin cambios) ---
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show()
                abrirCamara()
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_LONG).show()
            }
        }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "Foto capturada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segundapantalla)

        // --- LÓGICA DE BOTONES EXISTENTES (Cámara y Sonido) ---
        val btnCamera = findViewById<ImageButton>(R.id.btn_camera)
        btnCamera?.setOnClickListener {
            solicitarPermisoDeCamara()
        }

        // Este botón ya lleva a la pantalla de sonido, lo dejamos como está.
        val botonSonido = findViewById<ImageButton>(R.id.btn_sonido)
        botonSonido?.setOnClickListener {
            val intent = Intent(this, SonidoActivity::class.java)
            startActivity(intent)
        }

        // --- LÓGICA DEL MENÚ DESPLEGABLE ---
        val botonMenu = findViewById<ImageButton>(R.id.btn_menu)
        botonMenu.setOnClickListener {
            val popup = PopupMenu(this, botonMenu)
            popup.menuInflater.inflate(R.menu.menu_desplegable, popup.menu)

            popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    // === INICIO DE LA MODIFICACIÓN ===
                    R.id.opcion1_perfil -> {
                        // Navega a la pantalla de Sonido.
                        val intent = Intent(this, SonidoActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    // === FIN DE LA MODIFICACIÓN ===
                    R.id.opcion2_configuracion -> {
                        Toast.makeText(this, "Has seleccionado 'Detectar imagen'", Toast.LENGTH_SHORT).show()
                        // TODO: Aquí iría la lógica para abrir la pantalla de imagen.
                        true
                    }
                    R.id.opcion3_historial -> {
                        Toast.makeText(this, "Has seleccionado 'Historial'", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.opcion4_ayuda -> {
                        Toast.makeText(this, "Has seleccionado 'Configuracion'", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.opcion5_salir -> {
                        Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show()
                        finish() // Cierra la pantalla actual
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    // --- FUNCIONES PARA LA CÁMARA (Sin cambios) ---
    private fun solicitarPermisoDeCamara() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                abrirCamara()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                Toast.makeText(this, "Necesitamos permiso de la cámara para usar esta función.", Toast.LENGTH_LONG).show()
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun abrirCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }
}








