package car.copernic.pcanton.proyecto1.mostrar_anuncio

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.alertas.fragment_alerta
import car.copernic.pcanton.proyecto1.databinding.FragmentMensajesBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentMostrarAnuncioBinding


class mostrar_anuncio : Fragment() {
    private lateinit var binding: FragmentMostrarAnuncioBinding
    private var inputText: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentMostrarAnuncioBinding.inflate(inflater, container, false)



        return binding.root
    }
    companion object {
        fun newInstance(): mostrar_anuncio = mostrar_anuncio()
    }
}

