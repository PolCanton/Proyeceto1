package car.copernic.pcanton.proyecto1.alertas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import car.copernic.pcanton.proyecto1.adapter.Ventas_Adapter
import car.copernic.pcanton.proyecto1.databinding.FragmentAlertaBinding


class fragment_alerta : Fragment() {
    private lateinit var binding: FragmentAlertaBinding
    lateinit var mAdapter: Ventas_Adapter
    lateinit var email: String
    private lateinit var viewModel: fragment_alertaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlertaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(fragment_alertaViewModel::class.java)
        viewModel.cargarDatos(requireContext(), binding.recyclerViewAlerta)
        mAdapter = viewModel.getMAdapter()

        return binding.root
    }

    companion object {
        fun newInstance(): fragment_alerta = fragment_alerta()
    }

}