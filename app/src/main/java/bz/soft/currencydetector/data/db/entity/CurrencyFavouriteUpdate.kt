package bz.soft.currencydetector.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "currency_favourite_update", primaryKeys = ["timestamp"])
data class CurrencyFavouriteUpdate(
    @ColumnInfo(name = "timestamp") val timestamp: String,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean
)