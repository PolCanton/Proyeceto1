package car.copernic.pcanton.proyecto1.info_cuenta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import car.copernic.pcanton.proyecto1.Modelo.Anuncio
import car.copernic.pcanton.proyecto1.Modelo.cuenta
import car.copernic.pcanton.proyecto1.R
import car.copernic.pcanton.proyecto1.adapter.Anuncio_Adapter
import car.copernic.pcanton.proyecto1.adapter.Cuenta_Adapter
import car.copernic.pcanton.proyecto1.cuenta.fragment_cuenta
import car.copernic.pcanton.proyecto1.databinding.FragmentCuentaBinding
import car.copernic.pcanton.proyecto1.databinding.FragmentInfoCuentaBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase


class Info_cuenta : Fragment() {
    private lateinit var binding: FragmentInfoCuentaBinding
    private lateinit var auth: FirebaseAuth
    lateinit var mFirestore: FirebaseFirestore
    lateinit var cAdapter: Cuenta_Adapter
    lateinit var mRecycler: RecyclerView
    lateinit var query: Query

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInfoCuentaBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        var correo = get_email()

        mFirestore = FirebaseFirestore.getInstance()
        mRecycler = binding.recyclerViewInfoCuenta
        mRecycler.layoutManager = GridLayoutManager(context,1)
        query = mFirestore.collection("user").whereEqualTo("correo",correo  )
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<cuenta> =
            FirestoreRecyclerOptions.Builder<cuenta>().setQuery(query,
                cuenta::class.java).build()
        cAdapter =Cuenta_Adapter(firestoreRecyclerOptions)
        mRecycler.adapter = cAdapter
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

    override fun onStop() {
        super.onStop()
        cAdapter.startListening()
    }

    override fun onStart() {
        super.onStart()
        cAdapter.startListening()
    }
}