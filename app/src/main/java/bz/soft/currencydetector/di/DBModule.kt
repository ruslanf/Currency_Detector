package bz.soft.currencydetector.di

import android.app.Application
import bz.soft.currencydetector.data.db.RoomDB
import bz.soft.currencydetector.data.db.dao.CurrenciesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    fun provideDB(application: Application): RoomDB = RoomDB.getDataBase(application)
    fun provideCurrenciesDao(db: RoomDB): CurrenciesDao = db.currenciesDao()

    single { provideDB(application = androidApplication()) }
    single { provideCurrenciesDao(db = get()) }
}