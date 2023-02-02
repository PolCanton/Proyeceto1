package car.copernic.pcanton.proyecto1.alertas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.databinding.ActivityIniciarSessionBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentAlertaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class fragment_alerta : Fragment() {
    private lateinit var binding: FragmentAlertaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentAlertaBinding.inflate(layoutInflater)

    }
    companion object {
        fun newInstance(): fragment_alerta = fragment_alerta()
    }
}