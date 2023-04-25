package car.copernic.pcanton.proyecto1.cuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import car.copernic.pcanton.proyecto1.Mis_anuncios.Mis_Anuncios
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.buscar.fragment_buscar
import car.copernic.pcanton.proyecto1.databinding.FragmentAlertaBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentCuentaBinding
import car.copernic.pcanton.proyecto1.info_cuenta.Info_cuenta
import car.copernic.pcanton.proyecto1.mensajes.fragment_mensajes
import car.copernic.pcanton.proyecto1.mostrar_anuncio.mostrar_anuncio
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class fragment_cuenta : Fragment() {
    private lateinit var binding: FragmentCuentaBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: fragment_cuentaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentCuentaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(fragment_cuentaViewModel::class.java)
        auth = Firebase.auth

        binding.btnCuenta.setOnClickListener{
            val fragment = Info_cuenta.newInstance()
            mostrar_fragmento(fragment)
        }
        binding.btnMisAnuncios.setOnClickListener{
            val fragment = Mis_Anuncios.newInstance()
            mostrar_fragmento(fragment)
        }
        return binding.root
    }
    private fun  mostrar_fragmento(fragment: Fragment) {
        val transaction = getParentFragmentManager().beginTransaction()
        transaction.replace(R.id.frame_layout_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        fun newInstance(): fragment_cuenta = fragment_cuenta()
    }
}