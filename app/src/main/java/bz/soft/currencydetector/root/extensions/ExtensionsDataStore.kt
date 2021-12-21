package bz.soft.currencydetector.root.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val USER_DATA_STORAGE = "user_data.pb"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = USER_DATA_STORAGE
)