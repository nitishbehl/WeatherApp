package com.behl.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import composable.WeatherDetails
import kotlinx.coroutines.launch

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = viewModel()

            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    mainViewModel.getWeatherApi()
                }
            }
            val weatherResponse = remember { mainViewModel.weatherResponse }
            if (weatherResponse.value == null) {
                CircularProgressIndicator()
            } else {
                WeatherDetails(weatherResponse)
            }
        }
    }

    companion object {
        fun newIntent(activity: Activity): Intent {
            return Intent(activity, DetailsActivity::class.java)
        }
    }
}