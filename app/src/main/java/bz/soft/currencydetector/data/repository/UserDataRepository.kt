package bz.soft.currencydetector.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataRepository(
    private val dataStore: DataStore<Preferences>
) : UserData {

    override val watchToken: Flow<String>
        get() = dataStore
            .data
            .map { preferences ->
                preferences[TOKEN_KEY] ?: ""
            }

    override val watchExpiresIn: Flow<Int>
        get() = dataStore
            .data
            .map { preferences ->
                preferences[TOKEN_EXPIRE_TIME_KEY] ?: 0
            }

    override val watchAppTheme: Flow<Boolean>
        get() = dataStore
            .data
            .map { preferences ->
                preferences[DARK_THEME_KEY] ?: false
            }

    override val watchUserLogin: Flow<String>
        get() = dataStore
            .data
            .map { preferences ->
                preferences[USER_LOGIN_KEY] ?: ""
            }

    override suspend fun updateToken(token: String) {
        edit(TOKEN_KEY, token)
    }

    override suspend fun updateExpiresIn(time: Int) {
        edit(TOKEN_EXPIRE_TIME_KEY, time)
    }

    override suspend fun updateAppTheme(isDarkTheme: Boolean) {
        edit(DARK_THEME_KEY, isDarkTheme)
    }

    override suspend fun updateUserLogin(userLogin: String) {
        edit(USER_LOGIN_KEY, userLogin)
    }

    override suspend fun clearDataStore() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun clearToken() {
        clear(TOKEN_KEY)
    }

    override suspend fun clearExpireTime() {
        clear(TOKEN_EXPIRE_TIME_KEY)
    }

    override suspend fun clearAppTheme() {
        clear(DARK_THEME_KEY)
    }

    override suspend fun clearUserLogin() {
        clear(USER_LOGIN_KEY)
    }

    private suspend fun <T> edit(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    private suspend fun <T> clear(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.takeIf { it.contains(key) }?.remove(key)
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val TOKEN_EXPIRE_TIME_KEY = intPreferencesKey("expires_in")
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
        private val USER_LOGIN_KEY = stringPreferencesKey("user_login")
    }
}