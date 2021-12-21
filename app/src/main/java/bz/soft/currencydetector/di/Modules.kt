package bz.soft.currencydetector.di

import bz.soft.currencydetector.root.App
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val applicationModule = module {
    single { androidApplication() as App }
}