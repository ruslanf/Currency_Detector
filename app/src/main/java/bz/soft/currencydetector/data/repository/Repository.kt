package bz.soft.currencydetector.data.repository

import bz.soft.currencydetector.data.http.Either
import bz.soft.currencydetector.data.models.CurrencyModel

interface Repository {
    suspend fun getRates(): Either<Exception, CurrencyModel>
}