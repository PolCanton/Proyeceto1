package car.copernic.pcanton.proyecto1.Regsitrar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import car.copernic.pcanton.proyecto1.Iniciar.Iniciar_Session
import car.copernic.pcanton.proyecto1.databinding.ActivityIniciarSessionBinding
import car.copernic.pcanton.proyecto1.databinding.ActivityRegistrarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class Registrar : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarBinding
    private lateinit var auth: FirebaseAuth
    private var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.botonCrearCuenta.setOnClickListener { botonCrearCuentaOnClick() }
        binding.imagenVolver.setOnClickListener{ imagenVolverOnClick() }
    }
    private fun imagenVolverOnClick() {
        volver()
    }

    private fun botonCrearCuentaOnClick() {
        crearcuenta()
    }
    private fun crearcuenta() {

        val mEmail = binding.emailEditTextCrearCuenta.text.toString()
        val mPassword = binding.passwordEditTextCrearCuenta.text.toString()

        val passwordRegex = Pattern.compile("(?=.*[a-z A-Z 0-9]).{8,}$")


        if(mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            Toast.makeText(this, "Ingrese un email valido.",
                Toast.LENGTH_SHORT).show()
        } else if (mPassword.isEmpty()|| !passwordRegex.matcher(mPassword).matches()){
            Toast.makeText(this, "La contraseña es debil.",
                Toast.LENGTH_SHORT).show()
        } else {
            createAccount(mEmail, mPassword)
        }
    }
    private fun createAccount(email: String, password: String) {
        auth= Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    insert_basedatos()
                    volver()
                } else {
                    Toast.makeText(this, "No se pudo crear la cuenta. Vuelva a intertarlo",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun insert_basedatos() {
        val correo = binding.emailEditTextCrearCuenta.text.toString()
        val direccion=binding.direccionEditTextCrearCuenta.text.toString()
        val telefono=binding.telefonoEditTextCrearCuenta.text.toString()
        val nombre=binding.nombre.text.toString()

        db.collection("user").document(correo).set(
            hashMapOf( "correo" to correo,
                "nombre" to nombre,
                "direccion" to direccion,
                "telefono" to telefono,
            )).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Cuenta creada", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "No se han podido creada la cuenta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun volver() {
        val intent = Intent(this, Iniciar_Session::class.java)
        this.startActivity(intent)
    }
}