package car.copernic.pcanton.proyecto1.alertas

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class fragment_alertaViewModel : ViewModel() {

     fun get_email(): String? {
        val user = Firebase.auth.currentUser
        var email: String? = null
        user?.let {
            email = it.email
        }
        return email
    }
}