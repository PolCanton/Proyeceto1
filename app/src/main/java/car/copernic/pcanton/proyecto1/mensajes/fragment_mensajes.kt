package car.copernic.pcanton.proyecto1.mensajes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import car.copernic.pcanton.proyecto1.databinding.FragmentMensajesBinding


class fragment_mensajes : Fragment() {
    private lateinit var binding: FragmentMensajesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMensajesBinding.inflate(inflater, container, false)



        return binding.root
    }

    companion object {
        fun newInstance(): fragment_mensajes = fragment_mensajes()
    }
}