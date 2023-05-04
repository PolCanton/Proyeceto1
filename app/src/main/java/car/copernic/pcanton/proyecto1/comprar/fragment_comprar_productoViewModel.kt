package car.copernic.pcanton.proyecto1.comprar

import android.R
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import car.copernic.pcanton.proyecto1.buscar.fragment_buscarViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*

class fragment_comprar_productoViewModel : ViewModel() {

    private val mFirestore = FirebaseFirestore.getInstance()

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion

    private var _correoVendedor =MutableLiveData<String>()
    var correoVendedor: LiveData<String> = _correoVendedor



    private lateinit var email:String
    private var _nombreProducto =MutableLiveData<String>()
    var nombre_producto: LiveData<String> = _nombreProducto
    lateinit var nombrep:String
    lateinit var Correo:String

    private fun getnombreProducto(id: String){
        mFirestore.collection("anuncios").whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    nombrep= document.getString("nombre").toString()
                    Log.d("error", "getnombreProducto si funciona")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("error", "getnombreProducto no funciona")
            }
    }
    private fun getcorreoVendedor(id: String){
        mFirestore.collection("anuncios")
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    Correo= document.getString("vendedor").toString()
                    Log.d("error", "getcorreoVendedor si funciona")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("error", "getcorreoVendedor no funciona")
            }
    }

    fun getVendedorYNombre(id: String) {
        val docRef = mFirestore.collection("anuncios").document(id)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val data = documentSnapshot.data
            Correo = (data?.get("vendedor") as? String).toString()
            nombrep = (data?.get("nombre") as? String).toString()
        }.addOnFailureListener { exception ->
            Log.d("error", "getcorreoVendedor no funciona")

        }
    }
    fun insertarCompra(direccion: String,idProducto:String) {
//        getcorreoVendedor(idProducto)
//        getnombreProducto(idProducto)
        getVendedorYNombre(idProducto)
        get_email()

        val uniqueID = UUID.randomUUID().toString()
        mFirestore.collection("compras").document(uniqueID).set(
            hashMapOf(
                "idproducto" to idProducto,
                "direccion" to direccion,
                "vendedor" to Correo,
                "comprador" to email,
                "nombre" to nombrep
            )
        )
    }
    fun getDireccion() {
        get_email()
        mFirestore.collection("user").whereEqualTo("correo",email)
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

    private  fun get_email() {
        val user = Firebase.auth.currentUser
        user?.let {
            email = it.email.toString()
        }
    }
}