package com.example.zoonari

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SegundaPantallaActivity : AppCompatActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show()
                abrirCamara()
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segundapantalla)

        val btnCamera = findViewById<ImageButton>(R.id.btn_camera)
        btnCamera?.setOnClickListener {
            solicitarPermisoDeCamara()
        }
    }

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
        startActivityForResult(intent, CODIGO_CAMARA)
    }

    companion object {
        private const val CODIGO_CAMARA = 1001
    }
}





