package car.copernic.pcanton.proyecto1.buscar

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class fragment_buscarViewModel : ViewModel() {

    fun get_email(): String? {
        val user = Firebase.auth.currentUser
        var email: String? = null
        user?.let {
            email = it.email
        }
        return email.toString()
    }

    private val mFirestore = FirebaseFirestore.getInstance()
    private lateinit var mAdapter: Anuncio_Adapter

    fun cargarDatos(context: Context, options: String, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        val query = mFirestore.collection("anuncios")
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Anuncio> =
            FirestoreRecyclerOptions.Builder<Anuncio>()
                .setQuery(query, Anuncio::class.java).build()
        mAdapter = Anuncio_Adapter(firestoreRecyclerOptions, options)
        recyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    fun getMAdapter(): Anuncio_Adapter {
        return mAdapter
    }

}