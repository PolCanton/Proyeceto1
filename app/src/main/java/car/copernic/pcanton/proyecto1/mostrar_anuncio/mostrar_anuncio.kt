package car.copernic.pcanton.proyecto1.mostrar_anuncio

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import car.copernic.pcanton.proyecto1.databinding.FragmentMostrarAnuncioBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors


class mostrar_anuncio : Fragment() {
    private lateinit var binding: FragmentMostrarAnuncioBinding
    private var inputText: String? = ""
    lateinit var mFirestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentMostrarAnuncioBinding.inflate(inflater, container, false)

        inputText = arguments?.getString("nombre")
        mFirestore = FirebaseFirestore.getInstance()

        mFirestore.collection("anuncios").whereEqualTo("id",inputText)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->
                    Log.d(ContentValues.TAG, "Read document with ID ${  document.data["descripcion"]}")
                    Log.d(ContentValues.TAG, "Read document with ID ${  document.data["foto"]}")
                    Log.d(ContentValues.TAG, "Read document with ID ${  document.data["nombre"]}")
                    Log.d(ContentValues.TAG, "Read document with ID ${  document.data["precio"]}")
                    Log.d(ContentValues.TAG, "Read document with ID ${  document.data["ubicacion"]}")
                    Log.d(ContentValues.TAG, "Read document with ID ${  document.data["vendedor"]}")
                    val url="${document.data["foto"]}"
                    binding.textoNombre.text = "${document.data["nombre"]}"
                    binding.textoDescripcion.text = "${document.data["descripcion"]}"
                    binding.textoUbicacion.text = "${document.data["ubicacion"]}"
                    binding.textoPrecio.text = "${document.data["precio"]}"
                    getBitmapFromURL(url)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents $exception")
            }
        return binding.root
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
}

