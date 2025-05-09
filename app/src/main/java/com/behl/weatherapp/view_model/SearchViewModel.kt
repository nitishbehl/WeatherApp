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
import model.CityResponse

class SearchViewModel : ViewModel() {

    var cityResponse: MutableState<CityResponse?> = mutableStateOf(null)

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

