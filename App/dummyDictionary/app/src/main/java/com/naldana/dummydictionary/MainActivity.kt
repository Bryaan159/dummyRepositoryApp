package com.naldana.dummydictionary

import android.media.AudioPlaybackCaptureConfiguration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

//    Creacion de variables pra el menu lateral
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView
    private lateinit var drawableLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        setupNavigationView()
    }

    private fun setupNavigationView() {
//
        navigationView = findViewById(R.id.nav_view)
        navigationView.setupWithNavController(navController)
//        implementando funcion en la parte del fondo linea 65
        navigationView.setNavigationItemSelectedListener(this)
        fillNavigationView("Bryaan159")

    }

    private fun setupNavigation() {
//        Configurar ToolBar
        val toolbar:MaterialToolbar = findViewById(R.id.main_toolbar)
//        para que ponga como predeterminada mi toobar
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        drawableLayout = findViewById(R.id.main_drawer_layout)

        appBarConfiguration = AppBarConfiguration(
            setOf(
//                Solo aqui va a mostrar el menu hamburguesa
                R.id.wordListFragment
            ),
            drawableLayout
        )

//        configuracion final
        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
//        Para regresar hacia atras o navegar basicamente
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        Tomar accion
//        Solo tomara los id que tenga en navController
//        consultar si los id estan en navgraph
        return when(item.itemId){
            R.id.logOut ->{
                //TODO: LogOut ACTION
                true
            }else ->{
//                ???
                val handle = NavigationUI.onNavDestinationSelected(item,navController)
                if(handle) drawableLayout.close()
                handle
            }
        }

    }
//    Agregar el menu desde codigo
    private fun fillNavigationView(userNameText:String){
        val headerView = navigationView.getHeaderView(0)
    val userNameTextView:TextView = headerView.findViewById(R.id.nameMain)
    userNameTextView.text = userNameText

//    Llenar el menu
    navigationView.inflateMenu(R.menu.main_menu)
    }

}