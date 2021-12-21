package bz.soft.currencydetector.root.extensions

import android.content.Context
import android.content.Intent

inline fun <reified T : Any> Context.navigateToActivity(noinline init: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    intent.init()
    startActivity(intent)
}