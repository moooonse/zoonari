package com.example.zoonari

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrarseActivity : AppCompatActivity() {

    // Declara la variable para la autenticación de Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        // Inicializa Firebase Auth
        auth = Firebase.auth

        // Referencias a los campos de texto y al botón
        val campoNombre = findViewById<EditText>(R.id.input_nombre_registro)
        val campoEmail = findViewById<EditText>(R.id.input_email_registro)
        val campoPassword = findViewById<EditText>(R.id.input_password_registro)
        val botonCrearCuenta = findViewById<Button>(R.id.btn_crear_cuenta)

        // Configura el listener para el clic en el botón
        botonCrearCuenta.setOnClickListener {
            // Obtiene el texto de los campos, eliminando espacios en blanco
            val nombre = campoNombre.text.toString().trim()
            val email = campoEmail.text.toString().trim()
            val password = campoPassword.text.toString().trim()

            // Validación simple para asegurarse de que los campos no están vacíos
            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ¡LA MAGIA OCURRE AQUÍ!
            // Llama a Firebase para crear un nuevo usuario con el email y la contraseña
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // El usuario se creó correctamente
                        Toast.makeText(this, "Registro exitoso. ¡Bienvenido, $nombre!", Toast.LENGTH_SHORT).show()

                        // Opcional: Redirigir al usuario a la pantalla de inicio de sesión
                        val intent = Intent(this, InicioSesionActivity::class.java)
                        startActivity(intent)
                        finish() // Cierra la actividad de registro

                    } else {
                        // Si el registro falla, muestra un mensaje de error detallado
                        val errorMessage = task.exception?.message ?: "Error desconocido en el registro."
                        Toast.makeText(this, "Fallo en el registro: $errorMessage", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}

