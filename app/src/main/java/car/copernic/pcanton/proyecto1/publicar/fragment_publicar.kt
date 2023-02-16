package car.copernic.pcanton.proyecto1.publicar

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import car.copernic.pcanton.proyecto1.databinding.FragmentPublicarBinding
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.util.*


class fragment_publicar : Fragment() {
    private lateinit var binding: FragmentPublicarBinding
    lateinit var mFirestore: FirebaseFirestore
    lateinit var filePath: Uri
    lateinit  var storageReference: StorageReference
    lateinit var storage: FirebaseStorage
    private val PICK_IMAGE_REQUEST = 71
    lateinit  var  nombre:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentPublicarBinding.inflate(inflater, container, false)
        mFirestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference
        binding.buttonaceptar.setOnClickListener{
            uploadImage()
        }
        binding.imgView.setOnClickListener{
            chooseImage()
        }
        return binding.root
    }

    companion object {
        fun newInstance(): fragment_publicar = fragment_publicar()
    }

    private fun insert_basedatos(uri: String) {
        nombre = binding.exnombre.text.toString()
        val ubicacion=binding.exnombreubicacion.text.toString()
        val descipcion=binding.exnombredescripcion.text.toString()
        val precio=binding.exprecio.text.toString()
        val vendedor= get_email()
        val data = HashMap<String, Any>()



        mFirestore.collection("anuncios").document(nombre).set(
            hashMapOf("descripcion" to descipcion,
                "nombre" to nombre,
                "precio" to precio,
                "ubicacion" to ubicacion,
                "vendedor" to vendedor,
                "foto" to uri))


    }

    private  fun get_email(): String {
        val user = Firebase.auth.currentUser
        var email=""
        user?.let {
            email = it.email.toString()
        }
        return email
    }
    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    //   addUploadRecordToDb(downloadUri.toString())
                    insert_basedatos(downloadUri.toString())
                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(context, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }



    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            filePath = data.data!!
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, filePath)
                binding.imgView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}


