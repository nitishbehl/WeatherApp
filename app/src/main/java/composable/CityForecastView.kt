package composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun CityForecastView(
    city: String,
    date: String,
    temperature: Double,
    condition: String,
    alert: String
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xff87CEEB))
    ) {
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

@Preview(showBackground = true)
@Composable
fun CityForecastViewPreview() {
    CityForecastView(
        city = "Toronto",
        date = "May 11, 2025",
        temperature = 18.5,
        condition = "Cloudy",
        alert = "No alerts"
    )
}