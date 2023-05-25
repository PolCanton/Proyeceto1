package car.copernic.pcanton.proyecto1.buscar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.databinding.FragmentBuscarBinding


class fragment_buscar : Fragment() {

    private lateinit var binding: FragmentBuscarBinding
    lateinit var mAdapter: Anuncio_Adapter

    private lateinit var viewModel: fragment_buscarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBuscarBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(fragment_buscarViewModel::class.java)
        val options = "comprar"
        viewModel.cargarDatos(requireContext(), options, binding.recyclerViewAnuncios)
        mAdapter = viewModel.getMAdapter()

        return binding.root
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