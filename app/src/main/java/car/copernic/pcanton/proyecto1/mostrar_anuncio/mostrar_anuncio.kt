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
import car.copernic.pcanton.proyecto1.databinding.FragmentMostrarAnuncioBinding


class mostrar_anuncio : Fragment() {
    private lateinit var binding: FragmentMostrarAnuncioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentMostrarAnuncioBinding.inflate(inflater, container, false)

        val intent:Intent=Intent()
        val name: String? =intent.getStringExtra("name")
        binding.nombreAnuncio.setText(name)

        return binding.root


    }
}

