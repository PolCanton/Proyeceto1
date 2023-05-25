package car.copernic.pcanton.proyecto1.Mostrar_ofertas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import car.copernic.pcanton.proyecto1.databinding.FragmentMenuBinding


class fragment_menu_host : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.button5.setOnClickListener {

        }
        return binding.root
    }
}