package bz.soft.currencydetector.data.models.errors

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

data class AuthResponseError(
    @SerializedName("errors") val errors: @RawValue Errors,
    val error: @RawValue Error
)

data class Errors(
    @SerializedName("field_name") val fieldName: String? = "",
    @SerializedName("message") val message: String? = ""
)

data class Error(
    @SerializedName("error") val errorMessage: String? = ""
)