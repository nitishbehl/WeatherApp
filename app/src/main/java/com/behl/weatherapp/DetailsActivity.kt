package com.behl.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.behl.weatherapp.view_model.DetailsViewModel
import composable.WeatherDetailsScreen
import kotlinx.coroutines.launch
import model.WeatherIndex
import model.WeatherResponse

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val city = intent?.getStringExtra("city") ?: "Unknown City"
        setContent {
            val detailsViewModel: DetailsViewModel = viewModel()

            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    detailsViewModel.getWeatherApi(city)
                }
            }
            val weatherResponse = remember { detailsViewModel.weatherResponse }
            if (weatherResponse.value == null) {
                CircularProgressIndicator()
            } else {
                WeatherDetailsView(weatherResponse, city)
            }
        }
    }

    @Composable
    fun WeatherDetailsView(weatherResponse: MutableState<WeatherResponse?>, cityName: String) {
        val weather = weatherResponse.value

        val indexList = mutableListOf<WeatherIndex>(
            WeatherIndex(
                image = R.drawable.snow_moon, "Humidity",
                weather?.current?.humidity.toString()
            ),
            WeatherIndex(
                image = R.drawable.rainy_day, "Wind",
                weather?.current?.wind?.speed.toString()
            ),
            WeatherIndex(
                image = R.drawable.sun,
                "Sun",
                weather?.current?.uvIndex?.toString() ?: ""
            ),
        )

        WeatherDetailsScreen(
            city = weather?.location?.city?:"",
            day = weather?.forecast?.firstOrNull()?.day ?: "Unknown Day",
            date = weather?.date ?: "Unknown Date",
            temperature = weather?.current?.temperature?.value ?: 0.0,
            forecast = weather?.forecast?.filterNotNull() ?: emptyList(),
            alert = weather?.alerts?.firstOrNull()?.title ?: "No active alerts",
            condition = weather?.current?.weather?.main ?: "Unknown Condition",
            uvIndex = weather?.current?.uvIndex ?: 0.0,
            precipitation = weather?.current?.precipitation ?: 0.0,
            wind = weather?.current?.wind?.speed ?: 0.0,
            humidity = weather?.current?.humidity ?: 0,
            indexList = indexList

        )
    }

    companion object {
        fun newIntent(activity: Activity, city: String): Intent {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("city", city)
            return intent
        }
    }
}