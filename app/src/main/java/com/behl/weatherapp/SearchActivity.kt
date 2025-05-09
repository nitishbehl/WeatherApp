package com.behl.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.behl.weatherapp.view_model.MainViewModel
import composable.SearchPage
import kotlinx.coroutines.launch

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val cityResponse = remember { mainViewModel.cityResponse }
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
                            mainViewModel.getCityApi()
                            startActivity(
                                SearchResultActivity.newIntent(
                                    this@SearchActivity,
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