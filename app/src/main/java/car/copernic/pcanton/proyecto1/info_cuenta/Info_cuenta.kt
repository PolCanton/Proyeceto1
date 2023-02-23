package car.copernic.pcanton.proyecto1.info_cuenta

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import car.copernic.pcanton.proyecto1.databinding.FragmentInfoCuentaBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.WriteResult
import java.util.*


class Info_cuenta : Fragment() {
    private lateinit var binding: FragmentInfoCuentaBinding
    lateinit var mFirestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInfoCuentaBinding.inflate(inflater, container, false)

        mFirestore = FirebaseFirestore.getInstance()
        cargar_datos()
        binding.buttoneditar.setOnClickListener{editar_Datos()}
        binding.buttonaceptar.setOnClickListener{actualizar_datos()}
        return binding.root
    }

    private fun actualizar_datos() {
        val nombre=binding.exnombre.text.toString()
        val correo=binding.excorreo.text.toString()
        val direccion=binding.exdireccion.text.toString()
        val telefono=binding.extelefono.text.toString()

        mFirestore.collection("user").document(correo).set(
            hashMapOf( "correo" to correo,
                "nombre" to nombre,
                "direccion" to direccion,
                "telefono" to telefono,
              )).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Los datos se han actualizado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, "No se han podido actualizar los datos", Toast.LENGTH_SHORT).show()

            }
        }

    }
    private fun editar_Datos() {
        binding.exnombre.isEnabled=true
        binding.excorreo.isEnabled=true
        binding.exdireccion.isEnabled=true
        binding.extelefono.isEnabled=true

    }

    private fun cargar_datos() {
        val correo = get_email()
        mFirestore.collection("user").whereEqualTo("correo",correo)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    binding.exnombre.setText("${document.data["nombre"]}")
                    binding.excorreo.setText("${document.data["correo"]}")
                    binding.exdireccion.setText("${document.data["direccion"]}")
                    binding.extelefono.setText("${document.data["telefono"]}")

                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents $exception")
            }
    }

    companion object {
        fun newInstance(): Info_cuenta = Info_cuenta()
    }
    private  fun get_email(): String {
        val user = Firebase.auth.currentUser
        var email=""
        user?.let {
            email = it.email.toString()
        }
        return email
    }


}