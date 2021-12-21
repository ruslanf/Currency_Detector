package bz.soft.currencydetector.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import bz.soft.currencydetector.R
import bz.soft.currencydetector.databinding.ActivitySplashBinding
import bz.soft.currencydetector.root.Constants.SPLASH_TIME_OUT
import bz.soft.currencydetector.root.extensions.navigateToActivity
import bz.soft.currencydetector.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CurrencyDetector)
        super.onCreate(savedInstanceState)

        ActivitySplashBinding.inflate(layoutInflater).apply {
            setContentView(root)

            lifecycleScope.launch {
                delay(SPLASH_TIME_OUT)
                navigate(Navigation.Authorized)
            }
        }
    }

    private fun navigate(navigation: Navigation) {
        when (navigation) {
            Navigation.Authorized -> navigateToActivity<MainActivity> {  }
            Navigation.NotAuthorized -> Unit
        }
    }
}