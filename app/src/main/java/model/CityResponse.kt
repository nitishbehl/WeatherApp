package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(
    @Json(name = "cities")
    var cities: List<City?>? = listOf()
) {
    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "city")
        var city: String? = "", // Toronto
        @Json(name = "condition")
        var condition: String? = "", // Cloudy
        @Json(name = "datetime")
        var datetime: String? = "", // Mon, 10:15 AM
        @Json(name = "humidity")
        var humidity: String? = "", // 65%
        @Json(name = "temperature")
        var temperature: String? = "" // 22.5
    )
}