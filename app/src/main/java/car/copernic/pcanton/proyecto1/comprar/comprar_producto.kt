package car.copernic.pcanton.proyecto1.comprar

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.buscar.fragment_buscar
import car.copernic.pcanton.proyecto1.databinding.FragmentComprarProductoBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*


class comprar_producto : Fragment() {

    private lateinit var binding: FragmentComprarProductoBinding
    lateinit var mFirestore: FirebaseFirestore
lateinit var comprador:String
lateinit var email:String
lateinit var idproducto:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentComprarProductoBinding.inflate(inflater, container, false)
        mFirestore = FirebaseFirestore.getInstance()
        idproducto = arguments?.getString("id").toString()
        cargar_datos()
        getcorreoVendedor(idproducto)
        binding.btnComprar.setOnClickListener{
            insert_basedatos()
            val fragment = fragment_buscar.newInstance()
            val transaction = getParentFragmentManager().beginTransaction()
            transaction.replace(R.id.frame_layout_main, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return binding.root
    }

    companion object {
        fun newInstance(): comprar_producto = comprar_producto()
    }
    private fun cargar_datos() {
         get_email()
        mFirestore.collection("user").whereEqualTo("correo",email)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    binding.etLocation.setText("${document.data["direccion"]}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents $exception")
            }
    }

    private  fun get_email() {
        val user = Firebase.auth.currentUser
        user?.let {
            email = it.email.toString()
        }
    }

    private fun insert_basedatos() {
        get_email()
        val uniqueID = UUID.randomUUID().toString()
        var direccion=binding.etLocation.text.toString()
        mFirestore.collection("compras").document(uniqueID).set(
            hashMapOf(
                "idproducto" to idproducto,
                "direccion" to direccion,
            "vendedor" to email
          ))
        }

    private fun getcorreoVendedor(id: String) {
        mFirestore.collection("anuncios").whereEqualTo("id",id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->

                    Log.d(ContentValues.TAG, "Read document with ID ${  document.data["vendedor"]}")
            email="${  document.data["vendedor"]}"
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents $exception")
            }

    }

    }

