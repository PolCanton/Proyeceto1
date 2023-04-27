package car.copernic.pcanton.proyecto1.Iniciar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import car.copernic.pcanton.proyecto1.MainActivity
import car.copernic.pcanton.proyecto1.Regsitrar.Registrar
import car.copernic.pcanton.proyecto1.databinding.ActivityIniciarSessionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Iniciar_Session : AppCompatActivity() {
    private lateinit var binding: ActivityIniciarSessionBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: iniciar_sessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSessionBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(iniciar_sessionViewModel::class.java)
        viewModel.onStart()
        setContentView(binding.root)
        auth = Firebase.auth
        binding.signInAppCompatButton.setOnClickListener{ signInAppCompatButtonOnClick() }
        binding.cuentaTextView.setOnClickListener{ registrar() }
        viewModel.signInSuccess.observe(this) { success ->
            if (success) {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Error al iniciar session",Toast.LENGTH_LONG).show()

                //reload()
            }
        }

        viewModel.signInError.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
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
        viewModel.email.value = binding.emailEditText2.text.toString()
        viewModel.password.value = binding.passwordEditText2.text.toString()
        viewModel.signIn()
    }


}