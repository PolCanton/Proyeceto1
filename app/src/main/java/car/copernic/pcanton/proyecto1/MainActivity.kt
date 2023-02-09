package car.copernic.pcanton.proyecto1

import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceRequest.newInstance
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import car.copernic.pcanton.proyecto1.alertas.fragment_alerta
import car.copernic.pcanton.proyecto1.buscar.fragment_buscar
import car.copernic.pcanton.proyecto1.cuenta.fragment_cuenta
import car.copernic.pcanton.proyecto1.mensajes.fragment_mensajes
import car.copernic.pcanton.proyecto1.publicar.fragment_publicar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        val fragmentbuscar = fragment_buscar.newInstance()
        openFragment(fragmentbuscar)
        bottomNavigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener  { item ->
        when (item.itemId) {
            R.id.navigation_alertas -> {
                val fragmentAlerta = fragment_alerta.newInstance()
                openFragment(fragmentAlerta)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_buscar -> {
                val fragmentbuscar = fragment_buscar.newInstance()
                openFragment(fragmentbuscar)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cuenta -> {
                val fragmentCuenta = fragment_cuenta.newInstance()
                openFragment(fragmentCuenta)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mensajes -> {
                val fragmentMensajes = fragment_mensajes.newInstance()
                openFragment(fragmentMensajes)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_publicar -> {
                val fragmentPublicar = fragment_publicar.newInstance()
                openFragment(fragmentPublicar)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

}