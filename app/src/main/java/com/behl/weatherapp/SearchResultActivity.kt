package com.behl.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import composable.CityCard
import kotlinx.coroutines.launch
import model.CityResponse

class SearchResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CityCard(
                city = "kitchner",
                temperature = 22.0,
                onCardClick = {
                    lifecycleScope.launch {
                        startActivity(DetailsActivity.newIntent(this@SearchResultActivity))
                    }
                }
            )
        }
    }

    companion object {
        fun newIntent(activity: MainActivity, response: CityResponse): Intent {
            return Intent(activity, SearchResultActivity::class.java)
        }
    }
}