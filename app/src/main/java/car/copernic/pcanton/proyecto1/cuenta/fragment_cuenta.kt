package car.copernic.pcanton.proyecto1.cuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.buscar.fragment_buscar
import car.copernic.pcanton.proyecto1.databinding.FragmentAlertaBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentCuentaBinding


class fragment_cuenta : Fragment() {
    private lateinit var binding: FragmentCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCuentaBinding.inflate(layoutInflater)

    }
    companion object {
        fun newInstance(): fragment_cuenta = fragment_cuenta()
    }
}