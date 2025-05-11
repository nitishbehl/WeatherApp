package composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import model.WeatherResponse.Forecast

@Composable
internal fun WeekdayForecastView(
    forecast: List<Forecast>,
    condition: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0x80FFFFFF),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            items(forecast) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.day ?: "Unknown Day",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                       ){
                        Image(
                            painter = painterResource(id = icon(condition)),
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterEnd
                    ){
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
    }
}

@Preview()
@Composable
fun WeekdayForecastViewPreview() {
    WeekdayForecastView(
        forecast = listOf(
            Forecast(day = "Monday", time = "10:00 AM", temperature = 20.0),
            Forecast(day = "Tuesday", time = "12:00 PM", temperature = 22.0),
            Forecast(day = "Wednesday", time = "2:00 PM", temperature = 24.0),
            Forecast(day = "Thursday", time = "4:00 PM", temperature = 21.0),
            Forecast(day = "Friday", time = "6:00 PM", temperature = 19.0)
        ),
        condition = "Sunny",
    )
}
