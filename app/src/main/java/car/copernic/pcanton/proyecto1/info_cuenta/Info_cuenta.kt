package car.copernic.pcanton.proyecto1.info_cuenta

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import car.copernic.pcanton.proyecto1.cuenta.fragment_cuentaViewModel
import car.copernic.pcanton.proyecto1.databinding.FragmentInfoCuentaBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.WriteResult
import java.util.*


class Info_cuenta : Fragment() {
    private lateinit var binding: FragmentInfoCuentaBinding
    lateinit var mFirestore: FirebaseFirestore
    private lateinit var viewModel:fragment_info_cuentaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInfoCuentaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(fragment_info_cuentaViewModel::class.java)
        mFirestore = FirebaseFirestore.getInstance()
        viewModel.cargarDatos()
        binding.buttoneditar.setOnClickListener{editar_Datos()}
        binding.buttonaceptar.setOnClickListener{actualizar_datos()}
        viewModel.nombre.observe(viewLifecycleOwner) { nombre ->
            binding.exnombre.setText(nombre)
        }

        viewModel.correo.observe(viewLifecycleOwner) { correo ->
            binding.excorreo.setText(correo)
        }

        viewModel.direccion.observe(viewLifecycleOwner) { direccion ->
            binding.exdireccion.setText(direccion)
        }

        viewModel.telefono.observe(viewLifecycleOwner) { telefono ->
            binding.extelefono.setText(telefono)
        }
        return binding.root
    }

    private fun actualizar_datos() {
        val nombre=binding.exnombre.text.toString()
        val correo=binding.excorreo.text.toString()
        val direccion=binding.exdireccion.text.toString()
        val telefono=binding.extelefono.text.toString()
        viewModel.actualizarDatos(nombre,correo,direccion,telefono)
    }
    private fun editar_Datos() {
        binding.exnombre.isEnabled=true
        binding.excorreo.isEnabled=true
        binding.exdireccion.isEnabled=true
        binding.extelefono.isEnabled=true
    }



    companion object {
        fun newInstance(): Info_cuenta = Info_cuenta()
    }



}