package composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.behl.weatherapp.R
import com.behl.weatherapp.view_model.WeatherIndex
import model.WeatherResponse.Forecast

@Composable
fun WeatherDetailsScreen(
    city: String,
    day: String?,
    temperature: Double,
    forecast: List<Forecast>,
    alert: String,
    condition: String,
    date: String,
    uvIndex: Double,
    precipitation: Double,
    wind: Double,
    humidity: Int
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

        TodaysForecastView(forecast, condition)

        Spacer(modifier = Modifier.height(24.dp))

        WeekdayForecastView(forecast, condition)

        Spacer(modifier = Modifier.height(24.dp))


        GridBoxView(
            mutableListOf<WeatherIndex>(
                WeatherIndex(image = R.drawable.snow_moon, "Humidity", "60"),
                WeatherIndex(image = R.drawable.sun, "Wind", "10"),
                WeatherIndex(image = R.drawable.rainy_day, "Rainy", "20"),
                WeatherIndex(image = R.drawable.sun, "Sun", "20"),)
        )
    }
}



@Composable
private fun CityForecastView(
    city: String,
    date: String,
    temperature: Double,
    condition: String,
    alert: String
) {
    Column {
        Text(
            text = city,
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "${temperature}°",
            style = MaterialTheme.typography.displayLarge.copy(color = Color.White),
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = condition,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Feels like ${temperature}°",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0x80FFffff))
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = " $alert ",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
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
        forecast = listOf(
            Forecast(day = "Today", time = "10:00 AM", temperature = 20.0),
            Forecast(day = "Today", time = "12:00 PM", temperature = 22.0),
            Forecast(day = "Today", time = "2:00 PM", temperature = 24.0),
            Forecast(day = "Today", time = "4:00 PM", temperature = 21.0),
            Forecast(day = "Today", time = "6:00 PM", temperature = 19.0)
        ),
        alert = "No Alerts",
        condition = "Sunny",
        day = "Monday",
        uvIndex = 7.5,
        precipitation = 5.0,
        wind = 10.0,
        humidity = 60

    )
}
