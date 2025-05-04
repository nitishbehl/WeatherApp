package com.behl.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import composable.CityCard
import composable.SearchPage
import composable.WeatherDetails
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val mainViewModel: MainViewModel = viewModel()

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val cityResponse = remember { mainViewModel.cityResponse }
                var searchText by remember { mutableStateOf("") }


                if (cityResponse.value == null) {
                    SearchPage(
                        searchText,
                        onSearchChanged = { newText ->
                            lifecycleScope.launch {
                                mainViewModel.getCityApi()
                            }
                        }
                    )
                } else {

                    CityCard(
                        city = "kitchner",
                        temperature = 22.0,
                        onCardClick = {
                            lifecycleScope.launch {
                                mainViewModel.getWeatherApi()
                            }

                        }
                    )

                }

                val weatherResponse = remember { mainViewModel.weatherResponse }
                if (weatherResponse.value == null) {
                    CircularProgressIndicator()
                } else {
                    WeatherDetails(weatherResponse)
                }

            }
        }
    }
}

@Composable
private fun WeatherDetails(weatherResponse: MutableState<model.WeatherResponse?>) {
    val weather = weatherResponse.value

    WeatherDetails(
        city = weather?.location?.city ?: "Unknown City",
        date = weather?.date ?: "Unknown date",
        temperature = weather?.current?.temperature?.value ?: 0.0,
        feelsLike = weather?.current?.feelsLike?.value ?: 0.0,
        weatherDescription = weather?.current?.weather?.description ?: "No description",
        alert = "No alerts",
        forecast = weather?.forecast?.filterNotNull() ?: emptyList(),
        airQuality = "Good"
    )
}
