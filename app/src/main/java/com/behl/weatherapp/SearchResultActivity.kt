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
                condition = "sunny",
                datetime = "10:00",
                humidity = "22.0",
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
        fun newIntent(activity: SearchActivity, response: CityResponse): Intent {
            return Intent(activity, SearchResultActivity::class.java)
        }
    }
}