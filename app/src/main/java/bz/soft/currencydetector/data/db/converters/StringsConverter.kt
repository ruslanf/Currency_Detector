package bz.soft.currencydetector.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringsConverter {
    @TypeConverter
    fun fromList(list: List<String>): String? = Gson().toJson(list)

    @TypeConverter
    fun fromString(value: String?): List<String?>? =
        Gson().fromJson(value, object : TypeToken<List<String?>?>() {  }.type)
}