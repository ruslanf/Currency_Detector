package bz.soft.currencydetector.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import bz.soft.currencydetector.R
import bz.soft.currencydetector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CurrencyDetector)
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            binding = this

            navController.navInflater.inflate(R.navigation.navigation_graph).apply {
                startDestination = R.id.menuPopular
            }
            mainBottomNavigationMenu.setupWithNavController(navController)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()
}