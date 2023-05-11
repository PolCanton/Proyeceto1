package car.copernic.pcanton.proyecto1.Mis_anuncios

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class fragment_mis_anunciosViewModel : ViewModel() {

    private val mFirestore = FirebaseFirestore.getInstance()
    lateinit var query: Query
    lateinit var email:String
    private lateinit var mAdapter: Anuncio_Adapter


    private  fun get_email(): String {
        val user = Firebase.auth.currentUser
        var email=""
        user?.let {
            email = it.email.toString()
        }
        return email
    }

    fun cargarDatos(context: Context, recyclerView: RecyclerView) {
        var correo = get_email()
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        query = mFirestore.collection("anuncios").whereEqualTo("vendedor",correo)
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Anuncio> = FirestoreRecyclerOptions.Builder<Anuncio>()
            .setQuery(query, Anuncio::class.java).build()
        mAdapter = Anuncio_Adapter(firestoreRecyclerOptions, "editar")
        recyclerView.adapter = mAdapter
        mAdapter.startListening()
    }
    fun getMAdapter(): Anuncio_Adapter {
        return mAdapter
    }
}