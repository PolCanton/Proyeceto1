package car.copernic.pcanton.proyecto1.Mis_anuncios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.databinding.FragmentMisAnunciosBinding
import car.copernic.pcanton.proyecto1.mostrar_anuncio.mostrar_anuncio
import car.copernic.pcanton.proyecto1.publicar.fragment_publicar
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class Mis_Anuncios : Fragment() {

    private lateinit var binding: FragmentMisAnunciosBinding
    private lateinit var mViewModel: fragment_mis_anunciosViewModel
    lateinit var mAdapter: Anuncio_Adapter
    lateinit var mRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMisAnunciosBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this).get(fragment_mis_anunciosViewModel::class.java)
        mViewModel.cargarDatos(requireContext(), binding.recyclerViewMisanuncios)
        mAdapter = mViewModel.getMAdapter()
        return binding.root
    }
    companion object {
        fun newInstance(): Mis_Anuncios = Mis_Anuncios()
    }
    override fun onStart() {
        super.onStart()
        mAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter.stopListening()
    }
}