package car.copernic.pcanton.proyecto1.info_cuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.cuenta.fragment_cuenta
import car.copernic.pcanton.proyecto1.databinding.FragmentCuentaBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentInfoCuentaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class Info_cuenta : Fragment() {
    private lateinit var binding: FragmentInfoCuentaBinding
    private lateinit var auth: FirebaseAuth
    lateinit var mFirestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInfoCuentaBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        var correo = get_email()

        val citiesRef = mFirestore.collection("usuarios")
        val query = citiesRef.whereEqualTo("correo", correo)

        return binding.root
    }

    companion object {
        fun newInstance(): Info_cuenta = Info_cuenta()
    }
    private  fun get_email(): String {
        val user = Firebase.auth.currentUser
        var email=""
        user?.let {
            email = it.email.toString()
        }
        return email
    }

    private fun a(){
        val user = Firebase.auth.currentUser
        user?.let {
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl
            val emailVerified = it.isEmailVerified

            val uid = it.uid
        }
    }
}