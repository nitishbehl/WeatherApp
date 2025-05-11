package com.behl.weatherapp.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import model.WeatherResponse

class DetailsViewModel : ViewModel() {
    var weatherResponse: MutableState<WeatherResponse?> = mutableStateOf(null)

    suspend fun getWeatherApi(city: String): WeatherResponse? {
        val urlString = if (city == "Toronto") {
            "https://nitishbehl.github.io/Data/Weather_Toronto.json"
        } else if (city == "Vancouver") {
            "https://nitishbehl.github.io/Data/Weather_Vancouver.json"
        } else if (city == "Calgary") {
            "https://nitishbehl.github.io/Data/Weather_Calgary.json"
        } else if (city == "Montreal") {
            "https://nitishbehl.github.io/Data/Weather_Montreal.json"
        } else {
            "https://nitishbehl.github.io/Data/Weather_Halifax.json"
        }

        val client = HttpClient(OkHttp).get(urlString)
        val adapter = Moshi.Builder().build().adapter(WeatherResponse::class.java)
        val string = client.bodyAsText()
        Log.v("api response", string)
        val response = adapter.fromJson(string)
        weatherResponse.value = response
        return response
    }

}

