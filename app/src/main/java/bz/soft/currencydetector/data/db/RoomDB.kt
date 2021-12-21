package bz.soft.currencydetector.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bz.soft.currencydetector.data.db.converters.StringsConverter
import bz.soft.currencydetector.data.db.converters.UUIDConverter
import bz.soft.currencydetector.data.db.dao.CurrenciesDao
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.data.db.entity.CurrencyFavouriteUpdate
import bz.soft.currencydetector.root.Constants.DB_NAME

@Database(
    entities = [
        Currencies::class,
        CurrencyFavouriteUpdate::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(
    value = [
        UUIDConverter::class,
        StringsConverter::class
    ]
)
abstract class RoomDB : RoomDatabase() {

    abstract fun currenciesDao() : CurrenciesDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDataBase(context: Context): RoomDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDB(context).also { INSTANCE = it }
            }

        private fun createDB(context: Context) = Room
            .databaseBuilder(context.applicationContext, RoomDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}