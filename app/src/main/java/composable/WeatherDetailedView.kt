package composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Forecast

@Composable
fun WeatherDetails(
    city: String,
    date: String,
    temperature: Double,
    feelsLike: Double,
    weatherDescription: String,
    alert: String,
    forecast: List<Forecast>,
    airQuality: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))
                )
            )
    ) {

        Text(
            text = city,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF1565C0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "${temperature}°",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 90.sp,
                color = Color(0xFF0D47A1)
            )
        }

        Text(
            text = "Feels like ${feelsLike}° - $weatherDescription",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF1E88E5)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Alert",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD84315)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = alert,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF5D4037)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(modifier = Modifier.padding(8.dp)) {
            items(forecast.size) { index ->
                Column(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .background(Color(0xFFE1F5FE), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = forecast[index].time.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF0277BD)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${forecast[index].temperature}°C",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF01579B)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = " Air Quality : $airQuality",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color(0xFF2E7D32)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDetailsPreview() {
    WeatherDetails(
        city = "New York",
        date = "May 2, 2025",
        temperature = 22.5,
        feelsLike = 21.0,
        weatherDescription = "Clear Sky",
        alert = "No Alerts",
        forecast = listOf(
            Forecast(time = "10:00 AM", temperature = 20.0),
            Forecast(time = "12:00 PM", temperature = 22.0),
            Forecast(time = "2:00 PM", temperature = 24.0),
            Forecast(time = "4:00 PM", temperature = 21.0),
            Forecast(time = "6:00 PM", temperature = 19.0)
        ),
        airQuality = "Good"
    )
}
