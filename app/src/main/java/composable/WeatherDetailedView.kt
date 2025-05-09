package composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.behl.weatherapp.R
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
    humidity: Any
) {

    Column(
        modifier = Modifier
            .background(color = Color(0xff87CEEB))
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        Row {
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

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Today",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0x80FFFFFF), shape = RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(forecast) { item ->

                    Column(
                        modifier = Modifier.padding(
                            top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${item.temperature}°C",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painter = painterResource(id = icon(condition)),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = item.time.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0x80FFFFFF), shape = RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            LazyColumn {
                items(forecast) { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.day ?: "Unknown Day",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start
                        )
                        Image(
                            painter = painterResource(id = icon(condition)),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "${item.temperature}°C",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "UV Index Icon",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "UV Index",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = uvIndex.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF6C00)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rainy_day),
                        contentDescription = "Precipitation",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Precipitation",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = precipitation.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF6C00)
                    )
                }
            }

        }
        Row {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wind),
                        contentDescription = "wind Icon",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Wind",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = wind.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF6C00)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.snow_moon),
                        contentDescription = "Humidity",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Humidity",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = humidity.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF6C00)
                    )
                }
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
            Forecast(time = "10:00 AM", temperature = 20.0),
            Forecast(time = "12:00 PM", temperature = 22.0),
            Forecast(time = "2:00 PM", temperature = 24.0),
            Forecast(time = "4:00 PM", temperature = 21.0),
            Forecast(time = "6:00 PM", temperature = 19.0)
        ),
        alert = "No Alerts",
        condition = "Sunny",
        day = "Monday",
        uvIndex = 7.5,
        precipitation = 5.0,
        wind = 10.0,
        humidity = 60.0

    )
}
