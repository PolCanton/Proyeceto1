package car.copernic.pcanton.proyecto1.publicar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.databinding.FragmentMensajesBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentMostrarAnuncioBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentPublicarBinding


class fragment_publicar : Fragment() {
    private lateinit var binding: FragmentPublicarBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentPublicarBinding.inflate(inflater, container, false)


        return binding.root
    }
    companion object {
        fun newInstance(): fragment_publicar = fragment_publicar()
    }
}