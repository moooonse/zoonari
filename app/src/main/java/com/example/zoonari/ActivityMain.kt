package com.example.zoonari

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

// CORRECCIÓN 1: El nombre de la clase debe ser ActivityMain para coincidir con el AndroidManifest.xml
class ActivityMain : AppCompatActivity() {

    // --- LANZADOR PARA LA SOLICITUD DE PERMISOS ---
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Si el permiso se concede, abrimos la cámara.
                Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show()
                abrirCamara()
            } else {
                // Si se deniega, informamos al usuario.
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_LONG).show()
            }
        }

    // --- CORRECCIÓN 2: Se crea un nuevo lanzador para la CÁMARA usando la API moderna ---
    // Este lanzador abre la cámara y recibe la imagen como un Bitmap si la foto se toma con éxito.
    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // La foto se tomó correctamente.
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.get("data") as? Bitmap
                if (imageBitmap != null) {
                    // Si tenemos la imagen, la mostramos en un ImageView (opcional)
                    Toast.makeText(this, "Foto capturada con éxito", Toast.LENGTH_SHORT).show()
                    // Si tienes un ImageView en tu layout (ej: con id 'imageViewPreview'), puedes hacer esto:
                    // val imageView = findViewById<ImageView>(R.id.imageViewPreview)
                    // imageView.setImageBitmap(imageBitmap)
                }
            } else {
                // El usuario canceló la captura de la foto.
                Toast.makeText(this, "Captura de foto cancelada", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segundapantalla)

        val botonCamara = findViewById<ImageButton>(R.id.btn_camera)
        // Usamos el operador de llamada segura ?. para evitar crashes si no se encuentra el botón.
        botonCamara?.setOnClickListener {
            solicitarPermisoDeCamara()
        }
    }

    private fun solicitarPermisoDeCamara() {
        when {
            // Caso 1: El permiso ya está concedido, abrimos la cámara directamente.
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                abrirCamara()
            }
            // Caso 2: El usuario ya denegó el permiso, le damos una explicación.
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                Toast.makeText(this, "Necesitamos permiso de la cámara para usar esta función.", Toast.LENGTH_LONG).show()
                // Después de explicar, lanzamos la solicitud de permiso.
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            // Caso 3: Es la primera vez que se pide el permiso.
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun abrirCamara() {
        // CORRECCIÓN 3: Se usa el nuevo lanzador 'cameraLauncher' para abrir la cámara.
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }

    // El companion object y la constante CODIGO_CAMARA ya no son necesarios
    // companion object {
    //     private const val CODIGO_CAMARA = 1001
    // }
}




