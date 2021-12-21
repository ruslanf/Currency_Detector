package bz.soft.currencydetector.data.http

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class NetworkService(val context: Context) {

    private var wifiManager: WifiManager
    private var connectivityManager: ConnectivityManager

    init {
        Log.v("!!!!!!!!", "initializeWithApplicationContext()...")
        wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
//    fun initializeWithApplicationContext() {
//        Log.v("!!!!!!!!", "initializeWithApplicationContext()...")
//        wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    }

    // Helper that detects if online
    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.let {
            when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: false
    }
}