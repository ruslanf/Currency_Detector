package bz.soft.currencydetector.root.extensions

import bz.soft.currencydetector.data.models.RatesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * JSON converters
 */
fun toJson(data: Any?): String = Gson().toJson(data)

fun <T> fromJson(data: String?, type: Class<T>): T = Gson().fromJson(data, type)

fun fromJsonToMap(data: String?): Map<String, Float> = Gson().fromJson<Map<String, Float>>(data, RatesModel::class.java)

//convert a data class to a map
fun <T> T.serializeToMap(): Map<String, Float> = convert()

//convert a map to a data class
inline fun <reified T> Map<String, Float>.toDataClass(): T = convert()

val gson = Gson()
//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {  }.type)
}

/**
 * email validation
 */
private const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

fun validateEmail(email: String?): Boolean =
    if (!email.isNullOrEmpty()) email.matches(emailPattern.toRegex()) else false

fun validateEmailOrNull(email: String?): Boolean =
    email?.let {
        if (email.isNotEmpty()) email.matches(emailPattern.toRegex()) else false
    } ?: true
