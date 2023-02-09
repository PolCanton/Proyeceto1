package car.copernic.pcanton.proyecto1.alertas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.databinding.ActivityIniciarSessionBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentAlertaBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentBuscarBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class fragment_alerta : Fragment() {
    private lateinit var binding: FragmentAlertaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentAlertaBinding.inflate(inflater, container, false)



        return binding.root
    }
    companion object {
        fun newInstance(): fragment_alerta = fragment_alerta()
    }
}