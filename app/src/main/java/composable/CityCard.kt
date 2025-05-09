package composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.behl.weatherapp.R

@Composable
fun CityCard(
    city: String,
    condition: String,
    datetime: String,
    humidity: String,
    temperature: Double,
    onCityClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF929CDE))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = getWeatherIcon(condition)),
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = city,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${temperature}°C",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = datetime,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = Color(0xFFD94774))
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Humidity: $humidity",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = Color(0xFF6A75BA))
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Condition: $condition",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier
                            .width(100.dp)
                            .height(32.dp),
                        shape = RoundedCornerShape(8.dp),
                        onClick = { onCityClicked(city) }
                    ) {
                        Text(text = "VIEW STATS", fontSize = 10.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun getWeatherIcon(condition: String): Int {
    return when (condition.lowercase()) {
        "rainy" -> R.drawable.rainy_day
        "sunny" -> R.drawable.sun
        "cloudy" -> R.drawable.sun_cloud_angled_rain
        "snow" -> R.drawable.snow
        else -> R.drawable.moon_cloud_fast_wind
    }
}


@Preview(showBackground = true)
@Composable
fun CityCardPreview() {
    CityCard(
        city = "Kitchner",
        condition = "sunny",
        datetime = "10:00",
        humidity = "22.0",
        temperature = 22.0,
        onCityClicked = {

        }
    )

}


