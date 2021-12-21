package bz.soft.currencydetector.data.repository

import kotlinx.coroutines.flow.Flow

interface UserData {
    /**
     * Watch token insight DataStore
     */
    val watchToken: Flow<String>

    /**
     * Watch token expire time
     */
    val watchExpiresIn: Flow<Int>

    /**
     * Watch App Theme insight DataStore
     */
    val watchAppTheme: Flow<Boolean>

    /**
     * Watch User's login
     */
    val watchUserLogin: Flow<String>

    /**
     * Write token to DataStore
     */
    suspend fun updateToken(token: String)

    /**
     * Write expire token time
     */
    suspend fun updateExpiresIn(time: Int)

    /**
     * Set info Light or Dark theme
     */
    suspend fun updateAppTheme(isDarkTheme: Boolean = false)

    suspend fun updateUserLogin(userLogin: String)

    /**
     * Clear DataStore info
     */
    suspend fun clearDataStore()
    suspend fun clearToken()
    suspend fun clearExpireTime()
    suspend fun clearAppTheme()
    suspend fun clearUserLogin()
}