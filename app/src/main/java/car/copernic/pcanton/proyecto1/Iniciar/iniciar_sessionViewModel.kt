package car.copernic.pcanton.proyecto1.Iniciar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class iniciar_sessionViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val signInSuccess = MutableLiveData<Boolean>()
    val signInError = MutableLiveData<String>()

//    init {
//        signInSuccess.value = false
//        signInError.value = ""
//    }

    fun onStart() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                signInSuccess.value = true
            }
        }
    }

    fun signIn() {
        val emailVal = email.value
        val passwordVal = password.value

        if (emailVal.isNullOrEmpty() || passwordVal.isNullOrEmpty()) {
            signInError.value = "Email o contraseña incorrectos."
            return
        }

        auth.signInWithEmailAndPassword(emailVal!!, passwordVal!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    signInSuccess.value = true
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    signInError.value = "Email o contraseña incorrectos."
                }
            }
    }


}