package car.copernic.pcanton.proyecto1.Regsitrar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import car.copernic.pcanton.proyecto1.Iniciar.Iniciar_Session
import car.copernic.pcanton.proyecto1.databinding.ActivityIniciarSessionBinding
import car.copernic.pcanton.proyecto1.databinding.ActivityRegistrarBinding

class Registrar : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val intent = Intent(this, Iniciar_Session::class.java)
            this.startActivity(intent)
        }
    }
}