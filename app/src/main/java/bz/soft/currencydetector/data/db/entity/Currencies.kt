package bz.soft.currencydetector.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "currencies", primaryKeys = ["timestamp"])
data class Currencies(
    @ColumnInfo(name = "timestamp") val timestamp: String,
    @ColumnInfo(name = "base") val base: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "currency") val currencyName: String?,
    @ColumnInfo(name = "value") val currencyValue: Float?,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean = false
)
