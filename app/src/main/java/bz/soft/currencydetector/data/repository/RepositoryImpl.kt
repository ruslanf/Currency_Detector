package bz.soft.currencydetector.data.repository

import bz.soft.currencydetector.BuildConfig
import bz.soft.currencydetector.data.http.Either
import bz.soft.currencydetector.data.http.safeRequest
import bz.soft.currencydetector.data.models.CurrencyModel
import bz.soft.currencydetector.root.Constants.ACCESS_KEY
import bz.soft.currencydetector.root.Constants.ACCESS_KEY_VALUE
import bz.soft.currencydetector.root.Constants.API_MAIN_URL
import bz.soft.currencydetector.root.Constants.BASE_API
import bz.soft.currencydetector.root.Constants.DEV_API_URL
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RepositoryImpl(
    private val client: HttpClient
) : Repository {

    /**
     * API url
     */
    private val api = "${if (BuildConfig.DEBUG) DEV_API_URL else API_MAIN_URL}$BASE_API"

    override suspend fun getRates(): Either<Exception, CurrencyModel> =
        safeRequest {
            client.get(urlString = "$api/latest") {
                contentType(ContentType.Application.Json)
                parameter(ACCESS_KEY, ACCESS_KEY_VALUE)
            }
        }
}