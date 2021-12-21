package bz.soft.currencydetector.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import bz.soft.currencydetector.data.db.BaseDao
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.data.db.entity.CurrencyFavouriteUpdate
import kotlinx.coroutines.flow.Flow
import java.sql.Timestamp

@Dao
interface CurrenciesDao : BaseDao<Currencies> {

    @Query("Select * from currencies")
    fun getCurrencies(): Flow<List<Currencies>>

    @Query("Select * from currencies where isFavourite = 1")
    fun getFavourites(): Flow<List<Currencies>> //TODO add today in timestamp

    @Update(entity = Currencies::class)
    suspend fun doUpdateFavouriteCurrency(currencyFavouriteUpdate: CurrencyFavouriteUpdate)
}