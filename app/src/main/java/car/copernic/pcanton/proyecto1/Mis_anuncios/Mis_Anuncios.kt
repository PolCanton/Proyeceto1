package car.copernic.pcanton.proyecto1.Mis_anuncios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.databinding.FragmentMisAnunciosBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentPublicarBinding
import car.copernic.pcanton.proyecto1.mostrar_anuncio.mostrar_anuncio
import car.copernic.pcanton.proyecto1.publicar.fragment_publicar
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase


class Mis_Anuncios : Fragment() {
    private lateinit var binding: FragmentMisAnunciosBinding
    lateinit var mAdapter: Anuncio_Adapter
    lateinit var mRecycler: RecyclerView
    lateinit var mFirestore: FirebaseFirestore
    lateinit var query: Query
    lateinit var email:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMisAnunciosBinding.inflate(inflater, container, false)
        var correo = get_email()
        mFirestore = FirebaseFirestore.getInstance()
        mRecycler = binding.recyclerViewMisanuncios
        mRecycler.layoutManager = GridLayoutManager(context,1)
        query = mFirestore.collection("anuncios").whereEqualTo("vendedor",correo  )
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Anuncio> =
            FirestoreRecyclerOptions.Builder<Anuncio>().setQuery(query,
                Anuncio::class.java).build()
        mAdapter = Anuncio_Adapter(firestoreRecyclerOptions)
        mRecycler.adapter = mAdapter

        return binding.root
    }

    private  fun get_email(): String {
        val user = Firebase.auth.currentUser
        var email=""
        user?.let {
            email = it.email.toString()
        }
        return email
    }
    companion object {
        fun newInstance(): Mis_Anuncios = Mis_Anuncios()
    }
    override fun onStop() {
        super.onStop()
        mAdapter.startListening()
    }

    override fun onStart() {
        super.onStart()
        mAdapter.startListening()
    }
}