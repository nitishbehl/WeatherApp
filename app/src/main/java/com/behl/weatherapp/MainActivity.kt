package com.behl.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.behl.weatherapp.view_model.SearchViewModel
import composable.CityListView
import kotlinx.coroutines.launch
import model.CityResponse

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SearchViewModel = viewModel()
            val cityResponse = remember { viewModel.cityResponse }

            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    viewModel.getCityApi()
                }
            }
            if (cityResponse.value == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(48.dp))
                }
            } else {
                CityListView(cityResponse.value?.cities, onCityClicked = { city ->
                    Log.v("city clicked", city)
                    startActivity(DetailsActivity.newIntent(this@MainActivity, city))
                })
            }
        }
    }

    companion object {
        fun newIntent(activity: SearchActivity, cityName: String): Intent {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("city", cityName)
            return intent
        }
    }
}