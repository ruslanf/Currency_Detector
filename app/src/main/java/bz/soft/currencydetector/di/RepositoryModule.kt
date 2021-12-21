package bz.soft.currencydetector.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import bz.soft.currencydetector.data.db.RoomDB
import bz.soft.currencydetector.data.db.dao.CurrenciesDao
import bz.soft.currencydetector.data.repository.*
import bz.soft.currencydetector.root.extensions.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    fun provideDataBaseRepository(
        roomDB: RoomDB,
        currenciesDao: CurrenciesDao
    ): DataBaseRepository = DataBaseRepository(
        roomDB, currenciesDao
    )

    fun provideDataStore(
        context: Context
    ): DataStore<Preferences> = context.dataStore

    single {
        provideDataBaseRepository(
            roomDB = get(),
            currenciesDao = get()
        )
    }

    single { provideDataStore(context = androidContext()) }
    single<UserData> { UserDataRepository(dataStore = get()) }

    single<Repository> { RepositoryImpl(client = get()) }
}