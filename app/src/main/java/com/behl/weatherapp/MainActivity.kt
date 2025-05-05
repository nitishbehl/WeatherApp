@file:JvmName("SearchResultActivityKt")

package com.behl.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import composable.SearchPage
import composable.WeatherDetails
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val cityResponse = remember { mainViewModel.cityResponse }
            var searchText by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SearchPage(
                    searchText,
                    onSearchChanged = { searchText = it },
                    onSearchClick = {
                        lifecycleScope.launch {
                            mainViewModel.getCityApi()
                            startActivity(
                                SearchResultActivity.newIntent(
                                    this@MainActivity,
                                    cityResponse.value!!
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
internal fun WeatherDetails(weatherResponse: MutableState<model.WeatherResponse?>) {
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
