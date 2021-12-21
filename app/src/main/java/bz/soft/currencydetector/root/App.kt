package bz.soft.currencydetector.root

import android.app.Application
import bz.soft.currencydetector.di.*
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalCoroutinesApi
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    applicationModule,
                    networkModule,
                    dataBaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}