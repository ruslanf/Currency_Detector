package bz.soft.currencydetector.data.http

import bz.soft.currencydetector.data.repository.UserData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val dataStore: UserData
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request().newBuilder().apply {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/json")
                runBlocking {
                    dataStore
                        .watchToken
                        .filterNotNull()
                        .collect { token ->
                            addHeader("Authorization", "Bearer $token")
                        }
                }
            }.build()
        )
}