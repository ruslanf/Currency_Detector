package bz.soft.currencydetector.data.repository

import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.data.db.entity.CurrencyFavouriteUpdate
import bz.soft.currencydetector.data.http.Either
import kotlinx.coroutines.flow.Flow

interface DataBase {
    /**
     * Clear all data in DB
     */
    suspend fun clearAllData(): Either<Exception, Unit>

    /**
     * Work with Currencies
     */
    suspend fun insertCurrencies(currencies: Currencies): Either<Exception, Unit>
    suspend fun deleteCurrencies(currencies: Currencies): Either<Exception, Unit>
    suspend fun updateCurrencies(currencies: List<Currencies>): Either<Exception, Unit>
    fun watchCurrencies(): Flow<List<Currencies>>
    fun watchFavourites(): Flow<List<Currencies>>
    suspend fun updateFavouriteCurrency(currency: CurrencyFavouriteUpdate): Either<Exception, Unit>
}