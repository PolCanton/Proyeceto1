package car.copernic.pcanton.proyecto1.mensajes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.cuenta.fragment_cuenta
import car.copernic.pcanton.proyecto1.databinding.FragmentCuentaBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentMensajesBinding


class fragment_mensajes : Fragment() {
    private lateinit var binding: FragmentMensajesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMensajesBinding.inflate(layoutInflater)

    }
    companion object {
        fun newInstance(): fragment_mensajes = fragment_mensajes()
    }
}