package car.copernic.pcanton.proyecto1.comprar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.databinding.FragmentComprarProductoBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentMisAnunciosBinding
import car.copernic.pcanton.proyecto1.mostrar_anuncio.mostrar_anuncio


class comprar_producto : Fragment() {

    private lateinit var binding: FragmentComprarProductoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentComprarProductoBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        fun newInstance(): comprar_producto = comprar_producto()
    }
}