package car.copernic.pcanton.proyecto1.Mis_anuncios

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class fragment_mis_anunciosViewModel : ViewModel() {
    lateinit var mFirestore: FirebaseFirestore
    lateinit var query: Query
    lateinit var email:String

    fun getAnuncios() : Query {
        mFirestore = FirebaseFirestore.getInstance()
        var correo = get_email()
        query = mFirestore.collection("anuncios").whereEqualTo("vendedor",correo  )
        return query
    }

    private  fun get_email(): String {
        val user = Firebase.auth.currentUser
        var email=""
        user?.let {
            email = it.email.toString()
        }
        return email
    }
}