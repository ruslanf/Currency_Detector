package bz.soft.currencydetector.data.repository

import bz.soft.currencydetector.data.db.RoomDB
import bz.soft.currencydetector.data.db.dao.CurrenciesDao
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.data.db.entity.CurrencyFavouriteUpdate
import bz.soft.currencydetector.data.http.Either
import bz.soft.currencydetector.data.http.safeRequest
import kotlinx.coroutines.flow.Flow

class DataBaseRepository(
    private val roomDB: RoomDB,
    private val currenciesDao: CurrenciesDao
) : DataBase {
    /**
     * Clear all data in DB
     */
    override suspend fun clearAllData(): Either<Exception, Unit> =
        safeRequest { roomDB.clearAllTables() }

    /**
     * Work with Currencies
     */
    override suspend fun insertCurrencies(currencies: Currencies): Either<Exception, Unit> =
        safeRequest { currenciesDao.insert(currencies) }

    override suspend fun deleteCurrencies(currencies: Currencies): Either<Exception, Unit> =
        safeRequest { currenciesDao.delete(currencies) }

    override suspend fun updateCurrencies(currencies: List<Currencies>): Either<Exception, Unit> =
        safeRequest { currenciesDao.insert(*currencies.toTypedArray()) }

    override fun watchCurrencies(): Flow<List<Currencies>> = currenciesDao.getCurrencies()
    override fun watchFavourites(): Flow<List<Currencies>> = currenciesDao.getFavourites()

    override suspend fun updateFavouriteCurrency(currency: CurrencyFavouriteUpdate): Either<Exception, Unit> =
        safeRequest { currenciesDao.doUpdateFavouriteCurrency(currency) }
}