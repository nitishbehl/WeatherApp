package com.behl.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import composable.SearchPage
import kotlinx.coroutines.launch
import model.CityResponse

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var searchText by remember { mutableStateOf("") }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SearchPage(
                    searchText,
                    onSearchChanged = { searchText = it },
                    onSearchClick = {
                        lifecycleScope.launch {
                            startActivity(
                                MainActivity.newIntent(
                                    this@SearchActivity,
                                    searchText
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}