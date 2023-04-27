package car.copernic.pcanton.proyecto1.mostrar_anuncio

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.databinding.FragmentMostrarAnuncioBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.net.URL
import java.util.concurrent.Executors
import car.copernic.pcanton.proyecto1.comprar.comprar_producto
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference



class mostrar_anuncio : Fragment() {
    private lateinit var binding: FragmentMostrarAnuncioBinding
    private lateinit var id: String
    private lateinit var nombre: String
    private lateinit var opcion: String
    lateinit var filePath: Uri
    lateinit  var storageReference: StorageReference
    lateinit var mFirestore: FirebaseFirestore
    lateinit var url:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentMostrarAnuncioBinding.inflate(inflater, container, false)
        mFirestore = FirebaseFirestore.getInstance()
        val storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
        id = arguments?.getString("nombre").toString()
        opcion = arguments?.getString("opcion").toString()
        cargar_datos(id)
        if (opcion.equals("comprar")) {
            comprar_producto()

        } else if (opcion.equals("editar")) {
            editar_producto()

        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun nav_comprar() {
        val fragment = comprar_producto.newInstance()
        val args = Bundle()
        nombre=binding.textoNombre.text.toString()
        args.putString("id", nombre)
        fragment.arguments = args
        val transaction = getParentFragmentManager().beginTransaction()
        transaction.replace(R.id.frame_layout_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun editar_producto() {
        binding.bttnComprar.setText("Editar")
        binding.bttnComprar.setOnClickListener{
            if(binding.bttnComprar.text.equals("Editar")){
                binding.bttnComprar.setText("Aceptar")
               binding.textoNombre.isEnabled=true
                binding.textoDescripcion.isEnabled=true
                binding.textoPrecio.isEnabled=true
                binding.textoUbicacion.isEnabled=true

            }else if(binding.bttnComprar.text.equals("Aceptar")){
                insert_basedatos()
                binding.bttnComprar.setText("Editar")
                binding.textoNombre.isEnabled=false
                binding.textoDescripcion.isEnabled=false
                binding.textoPrecio.isEnabled=false
                binding.textoUbicacion.isEnabled=false
            }
        }
    }

    private fun comprar_producto() {
        binding.bttnComprar.setText("Comprar")
        binding.bttnComprar.setOnClickListener{
            nav_comprar()
        }
    }

    private fun cargar_datos(id: String) {
        mFirestore.collection("anuncios").whereEqualTo("id",id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                     url="${document.data["foto"]}"
                    binding.textoNombre.setText( "${document.data["nombre"]}")
                    binding.textoDescripcion.setText( "${document.data["descripcion"]}")
                    binding.textoUbicacion.setText( "${document.data["ubicacion"]}")
                    binding.textoPrecio.setText( "${document.data["precio"]}")
                    getBitmapFromURL(url)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents $exception")
            }

    }

    companion object {
        fun newInstance(): mostrar_anuncio = mostrar_anuncio()
    }

    fun getBitmapFromURL(src: String?) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null
        executor.execute {
            try {
                val `in` = URL(src).openStream()
                image = BitmapFactory.decodeStream(`in`)

                handler.post {
                    binding.imagenAnuncio.setImageBitmap(image)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    private fun insert_basedatos() {
        val nombre = binding.textoNombre.text.toString()
        val ubicacion=binding.textoUbicacion.text.toString()
        val descipcion=binding.textoDescripcion.text.toString()
        val precio=binding.textoPrecio.text.toString()
        val vendedor= get_email()

            mFirestore.collection("anuncios").document(id).set(
            hashMapOf("descripcion" to descipcion,
                "nombre" to nombre,
                "precio" to precio,
                "ubicacion" to ubicacion,
                "vendedor" to vendedor,
                "foto" to  url,
                "id" to id)).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Anuncio publicado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "No se ha podido publicar el anuncio", Toast.LENGTH_SHORT)
                    .show()
            }
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

}

