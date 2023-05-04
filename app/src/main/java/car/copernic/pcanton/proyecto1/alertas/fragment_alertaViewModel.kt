package car.copernic.pcanton.proyecto1.alertas

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.Modelo.Venta
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.adapter.Ventas_Adapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class fragment_alertaViewModel : ViewModel() {

     fun get_email(): String? {
        val user = Firebase.auth.currentUser
        var email: String? = null
        user?.let {
            email = it.email
        }
        return email
    }
    private val mFirestore = FirebaseFirestore.getInstance()
    private lateinit var mAdapter: Ventas_Adapter

    fun cargarDatos(context: Context,recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        val query = mFirestore.collection("compras")
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Venta> =
            FirestoreRecyclerOptions.Builder<Venta>().setQuery(query,
                Venta::class.java).build()
        mAdapter = Ventas_Adapter(firestoreRecyclerOptions)
        recyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    fun getMAdapter(): Ventas_Adapter {
        return mAdapter
    }
}