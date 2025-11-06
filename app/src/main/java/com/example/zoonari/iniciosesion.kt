package com.example.zoonari

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.zoonari.databinding.ActivityIniciosesionBinding
import com.google.firebase.auth.FirebaseAuth

class InicioSesionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIniciosesionBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciosesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            iniciarSesionConFirebase()
        }

        binding.txtRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarSesionConFirebase() {
        // === INICIO DE LA CORRECCIÓN ===
        // Usamos los IDs correctos de tu archivo XML: 'input_email' y 'input_password'

        val email = binding.inputEmail.text.toString().trim() // CAMBIADO de etEmail a inputEmail
        val password = binding.inputPassword.text.toString().trim() // CAMBIADO de etPassword a inputPassword

        // === FIN DE LA CORRECCIÓN ===

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese correo y contraseña.", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show()

                    // Navegamos a la siguiente pantalla (asumiendo que se llama SegundaPantallaActivity)
                    val intent = Intent(this, SegundaPantallaActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    AlertDialog.Builder(this)
                        .setTitle("Error de Autenticación")
                        .setMessage("Correo o contraseña incorrectos. Por favor, verifique sus datos.")
                        .setPositiveButton("Aceptar", null)
                        .show()
                }
            }
    }
}

