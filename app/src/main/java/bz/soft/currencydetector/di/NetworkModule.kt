@file:Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")

package bz.soft.currencydetector.di

import android.content.Context
import android.util.Log
import bz.soft.currencydetector.data.http.*
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import okhttp3.Cache
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.TlsVersion
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File
import java.util.*

private const val TIME_OUT = 60_000L

val networkModule = module {

    val connectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).apply {
        tlsVersions(TlsVersion.TLS_1_2)
        cipherSuites(
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
        )
    }.build()

    fun initKTorClient(
        context: Context,
        tokenInterceptor: TokenInterceptor,
        connectivityInterceptor: ConnectivityInterceptor,
        errorCodeInterceptor: ErrorCodeInterceptor
    ) = HttpClient(OkHttp) {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val httpCacheDirectory = File(context.cacheDir, "offlineCache")
        val httpCache = Cache(httpCacheDirectory, cacheSize)

        install(JsonFeature) {
            serializer = GsonSerializer {
                setPrettyPrinting()
                disableHtmlEscaping()
            }

            engine {
                config {
                    followRedirects(true)

                    cache(httpCache)
                    connectionSpecs(Collections.singletonList(connectionSpec))
                }
                addInterceptor(offlineCacheInterceptor(context))
                addInterceptor(tokenInterceptor)
                addInterceptor(connectivityInterceptor)
                addInterceptor(errorCodeInterceptor)
                addNetworkInterceptor(cacheInterceptor())

                clientCacheSize = (10 * 1024 * 1024)
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = TIME_OUT
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("KTor", "Logger Ktor => $message")
                }
            }
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
        }
    }

    factory { ConnectivityInterceptor(context = androidContext()) }
    factory { ErrorCodeInterceptor() }
    factory { TokenInterceptor(dataStore = get()) }

    single {
        initKTorClient(
            context = androidContext(),
            tokenInterceptor = get(),
            connectivityInterceptor = get(),
            errorCodeInterceptor = get()
        )
    }
}