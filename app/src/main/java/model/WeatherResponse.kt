package model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "current")
    var current: Current? = null,

    @Json(name = "date")
    var date: String? = "",

    @Json(name = "forecast")
    var forecast: List<Forecast> = emptyList(),

    @Json(name = "hourly_forecast")
    var hourlyForecast: List<HourlyForecast> = emptyList(),

    @Json(name = "location")
    var location: Location? = null
)

@JsonClass(generateAdapter = true)
data class Current(
    @Json(name = "feels_like")
    var feelsLike: FeelsLike? = null,

    @Json(name = "humidity")
    var humidity: Int? = 0,

    @Json(name = "pressure")
    var pressure: Int? = 0,

    @Json(name = "temperature")
    var temperature: Temperature? = null,

    @Json(name = "precipitation")
    var precipitation: Double? = 0.0,

    @Json(name = "uv_index")
    var uvIndex: Double? = 0.0,

    @Json(name = "visibility")
    var visibility: Int? = 0,

    @Json(name = "sunrise")
    var sunrise: String? = "",

    @Json(name = "sunset")
    var sunset: String? = "",

    @Json(name = "weather")
    var weather: Weather? = null,

    @Json(name = "wind")
    var wind: Wind? = null
)

@JsonClass(generateAdapter = true)
data class FeelsLike(
    @Json(name = "unit")
    var unit: String? = "",

    @Json(name = "value")
    var value: Double? = 0.0
)
@JsonClass(generateAdapter = true)
data class Temperature(
    @Json(name = "unit")
    var unit: String? = "",

    @Json(name = "value")
    var value: Double? = 0.0
)

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    var description: String? = "",

    @Json(name = "icon")
    var icon: String? = "",

    @Json(name = "main")
    var main: String? = ""
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "direction")
    var direction: String? = "",

    @Json(name = "speed")
    var speed: Double? = 0.0,

    @Json(name = "unit")
    var unit: String? = ""
)

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "temperature")
    var temperature: Double? = 0.0,

    @Json(name = "time")
    var time: String? = "",

    @Json(name = "weather")
    var weather: String? = ""
)

@JsonClass(generateAdapter = true)
data class HourlyForecast(
    @Json(name = "time")
    var time: String? = "",

    @Json(name = "temperature")
    var temperature: Double? = 0.0,

    @Json(name = "weather")
    var weather: Weather? = null
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "city")
    var city: String? = "",

    @Json(name = "coordinates")
    var coordinates: Coordinates? = Coordinates(),

    @Json(name = "country")
    var country: String? = ""
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    @Json(name = "latitude")
    var latitude: Double? = 0.0,

    @Json(name = "longitude")
    var longitude: Double? = 0.0
)
