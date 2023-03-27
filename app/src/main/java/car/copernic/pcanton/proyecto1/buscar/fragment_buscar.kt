package car.copernic.pcanton.proyecto1.buscar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.databinding.FragmentBuscarBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase


class fragment_buscar : Fragment() {

    private lateinit var binding: FragmentBuscarBinding
    lateinit var mAdapter: Anuncio_Adapter
    lateinit var mRecycler: RecyclerView
    lateinit var mFirestore: FirebaseFirestore
    lateinit var query: Query
    lateinit var email:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentBuscarBinding.inflate(inflater, container, false)
        get_email()
        val options="comprar"
        mFirestore = FirebaseFirestore.getInstance()
        mRecycler = binding.recyclerViewAnuncios
        mRecycler.layoutManager = GridLayoutManager(context,1)
        query = mFirestore.collection("anuncios")
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Anuncio> =
            FirestoreRecyclerOptions.Builder<Anuncio>().setQuery(query,
                Anuncio::class.java).build()
        mAdapter = Anuncio_Adapter(firestoreRecyclerOptions,options)
        mRecycler.adapter = mAdapter

        return binding.root
    }


    private  fun get_email() {
        val user = Firebase.auth.currentUser
        val let = user?.let {

            email = it.email.toString()

        }
    }

    companion object {
        fun newInstance(): fragment_buscar = fragment_buscar()
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