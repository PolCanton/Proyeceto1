package car.copernic.pcanton.proyecto1.comprar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*

class fragment_comprar_productoViewModel : ViewModel() {

    private val mFirestore = FirebaseFirestore.getInstance()

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion

    private var _correoVendedor = MutableLiveData<String>()
    var correoVendedor: LiveData<String> = _correoVendedor

    private var _imagen = MutableLiveData<String>()
    var imagen: LiveData<String> = _imagen
    private lateinit var email: String
    private var _nombreProducto = MutableLiveData<String>()
    var nombre_producto: LiveData<String> = _nombreProducto

    fun getVendedorYNombre(id: String, onComplete: () -> Unit) {
        mFirestore.collection("anuncios").whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    _nombreProducto.value = document.data["nombre"].toString()
                    _correoVendedor.value = document.data["vendedor"].toString()
                    _imagen.value = document.data["foto"].toString()
                }
                onComplete()
            }
            .addOnFailureListener { exception ->
                Log.w("error", "getVendedorYNombre no funciona")
            }
    }

    fun insertarCompra(direccion: String, idProducto: String) {
        getVendedorYNombre(idProducto) {
            get_email()

            val uniqueID = UUID.randomUUID().toString()
            mFirestore.collection("compras").document(uniqueID).set(
                hashMapOf(
                    "idproducto" to idProducto,
                    "direccion" to direccion,
                    "vendedor" to _imagen.value,
                    "comprador" to email,
                    "nombre" to _nombreProducto.value,
                    "foto" to _imagen.value
                )
            )
        }
    }

    fun getDireccion() {
        get_email()
        mFirestore.collection("user").whereEqualTo("correo", email)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    _direccion.value = document.getString("direccion")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("error", "getcorreoVendedor no cargarDatos")
            }
    }

    private fun get_email() {
        val user = Firebase.auth.currentUser
        user?.let {
            email = it.email.toString()
        }
    }
}