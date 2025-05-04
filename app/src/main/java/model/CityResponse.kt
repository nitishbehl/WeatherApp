package model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(
    @Json(name = "cities")
    var cities: List<City?> = emptyList()
) {
    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "city")
        var city: String? = "",
        @Json(name = "temperature")
        var temperature: String? = ""
    )
}
