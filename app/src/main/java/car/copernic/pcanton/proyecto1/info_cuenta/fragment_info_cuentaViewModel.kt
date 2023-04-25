package car.copernic.pcanton.proyecto1.info_cuenta

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class fragment_info_cuentaViewModel : ViewModel() {

    private val mFirestore = FirebaseFirestore.getInstance()
    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String>
        get() = _nombre

    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String>
        get() = _correo

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String>
        get() = _direccion

    private val _telefono = MutableLiveData<String>()
    val telefono: LiveData<String>
        get() = _telefono

    fun cargarDatos() {
        val correo = get_email()
        mFirestore.collection("user").whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    _nombre.value = "${document.data["nombre"]}"
                    _correo.value = document.data["correo"].toString()
                    _direccion.value = document.data["direccion"].toString()
                    _telefono.value = document.data["telefono"].toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents $exception")
            }
    }
    private  fun get_email(): String {
        val user = Firebase.auth.currentUser
        var email=""
        user?.let {
            email = it.email.toString()
        }
        return email
    }
    fun actualizarDatos(nombre: String, correo: String, direccion: String, telefono: String) {
        mFirestore.collection("user").document(correo).set(
            hashMapOf(
                "correo" to correo,
                "nombre" to nombre,
                "direccion" to direccion,
                "telefono" to telefono,
            )).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // hacer algo en caso de éxito
            } else {
                // hacer algo en caso de error
            }
        }
    }
}

