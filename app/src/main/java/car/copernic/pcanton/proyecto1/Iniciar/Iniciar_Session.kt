package car.copernic.pcanton.proyecto1.Iniciar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import car.copernic.pcanton.proyecto1.MainActivity
import car.copernic.pcanton.proyecto1.Regsitrar.Registrar
import car.copernic.pcanton.proyecto1.databinding.ActivityIniciarSessionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Iniciar_Session : AppCompatActivity() {
    private lateinit var binding: ActivityIniciarSessionBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.signInAppCompatButton.setOnClickListener{ signInAppCompatButtonOnClick() }
        binding.cuentaTextView.setOnClickListener{ registrar() }



    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }
        }
    }

    private fun registrar() {
        val intent = Intent(this, Registrar::class.java)
        this.startActivity(intent)
    }

    private fun reload() {
        val intent = Intent(this, Iniciar_Session::class.java)
        this.startActivity(intent)
    }

    private fun signInAppCompatButtonOnClick() {
        val email=binding.emailEditText2.text.toString()
        val pswrd=binding.passwordEditText2.text.toString()

        when {
            pswrd.isEmpty() || email.isEmpty() -> {
                Toast.makeText(this, "Email o contraseña o incorrectos.",
                    Toast.LENGTH_SHORT).show()
            }
            else -> {
                signIn(email, pswrd)
            }
        }
    }

    private fun signIn(email: String, pswrd: String) {
        auth.signInWithEmailAndPassword(email, pswrd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    val intent = Intent(this, MainActivity::class.java)
                    this.startActivity(intent)
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Email o contraseña o incorrectos.",
                        Toast.LENGTH_SHORT).show()
                    reload()
                }
            }
    }
}