package car.copernic.pcanton.proyecto1.alertas


import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.Modelo.Venta
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.adapter.Ventas_Adapter
import car.copernic.pcanton.proyecto1.databinding.FragmentAlertaBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import car.copernic.pcanton.proyecto1.alertas.fragment_alertaViewModel

class fragment_alerta : Fragment() {
    private lateinit var binding: FragmentAlertaBinding
    lateinit var mAdapter: Ventas_Adapter
    lateinit var mRecycler: RecyclerView
    lateinit var mFirestore: FirebaseFirestore
    lateinit var query: Query
    lateinit var email:String
    private lateinit var viewModel: fragment_alertaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentAlertaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(fragment_alertaViewModel::class.java)

        viewModel.get_email()
        //.whereEqualTo("vendedor",email)
        mFirestore = FirebaseFirestore.getInstance()
        mRecycler = binding.recyclerViewAlerta
        mRecycler.layoutManager = GridLayoutManager(context,1)
        query = mFirestore.collection("compras")
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Venta> =
            FirestoreRecyclerOptions.Builder<Venta>().setQuery(query,
                Venta::class.java).build()

        mAdapter = Ventas_Adapter(firestoreRecyclerOptions)
        mRecycler.adapter = mAdapter
        Log.d(TAG, "query size: ${firestoreRecyclerOptions.snapshots.size}")

        return binding.root
    }
    companion object {
        fun newInstance(): fragment_alerta = fragment_alerta()
    }



}