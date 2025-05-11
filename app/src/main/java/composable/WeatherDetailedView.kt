package composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.behl.weatherapp.R
import model.WeatherIndex
import model.WeatherResponse.Forecast

@Composable
fun WeatherDetailsScreen(
    city: String,
    temperature: Double,
    forecast: List<Forecast>,
    alert: String,
    condition: String,
    date: String,
    indexList: MutableList<WeatherIndex>
) {

    Column(
        modifier = Modifier
            .background(color = Color(0xff87CEEB))
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {

        CityForecastView(city, date, temperature, condition, alert)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Today",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TodayForecastView(forecast, condition)

        Spacer(modifier = Modifier.height(24.dp))

        WeekdayForecastView(forecast, condition)

        Spacer(modifier = Modifier.height(24.dp))


        GridBoxView(indexList)
    }
}




@Composable
fun icon(condition: String): Int {
    return when (condition.lowercase()) {
        "rainy" -> R.drawable.rainy_day
        "sunny" -> R.drawable.sun
        "cloudy" -> R.drawable.sun_cloud_angled_rain
        "snow" -> R.drawable.snow
        else -> R.drawable.moon_cloud_fast_wind
    }
}


@Preview()
@Composable
fun WeatherDetailsPreview() {
    WeatherDetailsScreen(
        city = "New York",
        date = "May 2, 2025",
        temperature = 22.5,
        condition = "Sunny",
        alert = "No Alerts",
        forecast = listOf(
            Forecast(day = "Today", time = "10:00 AM", temperature = 20.0),
            Forecast(day = "Today", time = "12:00 PM", temperature = 22.0),
            Forecast(day = "Today", time = "2:00 PM", temperature = 24.0),
            Forecast(day = "Today", time = "4:00 PM", temperature = 21.0),
            Forecast(day = "Today", time = "6:00 PM", temperature = 19.0)
        ),

        indexList = mutableListOf<WeatherIndex>(
            WeatherIndex(image = R.drawable.snow_moon, "Humidity", "60"),
            WeatherIndex(image = R.drawable.snow, "Wind", "10"),
            WeatherIndex(image = R.drawable.rainy_day, "Rainy", "20"),
            WeatherIndex(image = R.drawable.sun, "Sun", "20"),

            )
    )


}
