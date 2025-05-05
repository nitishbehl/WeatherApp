package com.behl.weatherapp

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import model.WeatherResponse
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.statement.bodyAsText
import model.CityResponse


class MainViewModel : ViewModel() {
    var weatherResponse: MutableState<WeatherResponse?> = mutableStateOf(null)
    var cityResponse: MutableState<CityResponse?> = mutableStateOf(null)


    suspend fun getWeatherApi(): WeatherResponse? {
        val client = HttpClient(OkHttp).get("https://nitishbehl.github.io/Data/Weather.json")
        val adapter = Moshi.Builder().build().adapter(WeatherResponse::class.java)
        val string = client.bodyAsText()
        Log.v("api response", string)
        val response = adapter.fromJson(string)
        weatherResponse.value = response
        return response
    }

    suspend fun getCityApi(): CityResponse? {
        val client = HttpClient(OkHttp).get("https://nitishbehl.github.io/Data/city.json")
        val adapter = Moshi.Builder().build().adapter(CityResponse::class.java)
        val string = client.bodyAsText()
        Log.v("api response", string)
        val response = adapter.fromJson(string)
        cityResponse.value = response
        return response
    }
}