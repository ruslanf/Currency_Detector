package bz.soft.currencydetector.root

object Constants {
    const val API_MAIN_URL = "http://api.exchangeratesapi.io"
    const val DEV_API_URL = "http://api.exchangeratesapi.io"
    const val BASE_API = "/v1"

    const val ACCESS_KEY = "access_key"
    const val ACCESS_KEY_VALUE = "18e43789482ee0a5bd0dbff58268ecea"

    const val PASSWORD_MIN_LENGTH = 5

    const val CACHE_CONTROL_DISABLE = "no-cache"

    const val PLATFORM = "android"

    const val DB_NAME = "currency-detector.db"

    const val MEDIA_FOLDER = "media"

    const val SPLASH_TIME_OUT: Long = 2 * 1000
    const val STORE_TIME_OUT: Long = 5 * 1000

    const val ERROR_SOUND = ".mp3"

    // Dialog
    const val DIALOG_MENU_TAG = "dialog_menu"

    const val OUT_DATE_FORMATTER = "dd.MM.yyyy"
    const val IN_DATE_FORMATTER = "yyyy-MM-dd"

    val DAYS = IntArray(31) { i -> i + 1 }
    val MONTH = IntArray(12) { i -> i + 1 }
    val YEAR = IntArray(200) { i -> 1900 + i + 1}

    const val EMPTY_STRING = ""
    const val ZERO_STRING = "0"

    const val DELAY_MILLS: Long = 600
}