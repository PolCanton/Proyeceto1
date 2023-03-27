package car.copernic.pcanton.proyecto1.alertas

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.databinding.ActivityIniciarSessionBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentAlertaBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentBuscarBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging

class fragment_alerta : Fragment() {
    private lateinit var binding: FragmentAlertaBinding

    @SuppressLint("StringFormatInvalid")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentAlertaBinding.inflate(inflater, container, false)

        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }


        return binding.root
    }
    companion object {
        fun newInstance(): fragment_alerta = fragment_alerta()
    }


}