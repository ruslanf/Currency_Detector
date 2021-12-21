package bz.soft.currencydetector.data.http

import okhttp3.Interceptor
import okhttp3.Response

class ErrorCodeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        when (response.code) {
            401 -> throw TokenSyncException()
        }
        return response
    }
}