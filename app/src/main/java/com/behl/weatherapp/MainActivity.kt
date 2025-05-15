package com.behl.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.behl.weatherapp.view_model.SearchViewModel
import composable.CityListView
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->

                val graph =
                    navController.createGraph(startDestination = Screen.Home.rout) {
                        composable(route = Screen.Home.rout) {
                            HomeScreeUI()
                        }
                        composable(route = Screen.Setting.rout) {

                        }

                        composable(route = Screen.Profile.rout) {

                        }
                    }
                NavHost(
                    navController = navController,
                    graph = graph,
                    modifier = Modifier.padding(innerPadding)
                )
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


    @Composable
    private fun HomeScreeUI() {
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



sealed class Screen(val rout: String) {
    object Home : Screen("home_screen")
    object Profile : Screen("profile_screen")
    object Setting : Screen("setting_screen")
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

