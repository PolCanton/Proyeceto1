package car.copernic.pcanton.proyecto1.comprar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.buscar.fragment_buscar
import car.copernic.pcanton.proyecto1.databinding.FragmentComprarProductoBinding


class comprar_producto : Fragment() {

    private lateinit var binding: FragmentComprarProductoBinding
    lateinit var idproducto: String
    private lateinit var viewModel: fragment_comprar_productoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentComprarProductoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(fragment_comprar_productoViewModel::class.java)
        idproducto = arguments?.getString("id").toString()
        viewModel.getDireccion()
        binding.btnComprar.setOnClickListener {
            val direccion = binding.etLocation.text.toString()
            Thread {
                viewModel.insertarCompra(direccion, idproducto)
            }.start()
            val fragment = fragment_buscar.newInstance()
            val transaction = getParentFragmentManager().beginTransaction()
            transaction.replace(R.id.frame_layout_main, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        viewModel.direccion.observe(viewLifecycleOwner) { direccion ->
            binding.etLocation.setText(direccion)
        }
        return binding.root
    }

    companion object {
        fun newInstance(): comprar_producto = comprar_producto()
    }


}

