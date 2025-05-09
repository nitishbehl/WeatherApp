package composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.behl.weatherapp.R
import com.behl.weatherapp.view_model.WeatherIndex

@Composable
fun ImageTextBoxView(string: String, image: Int, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Humidity",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = string,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFEF6C00)
        )
    }
}

@Composable
fun GridBoxView(list: List<WeatherIndex>) {
    LazyVerticalGrid (
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list.size) { index ->
            val obj = list[index]
            ImageTextBoxView(string = obj.string, image = obj.image, value = obj.value)
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun ImageTextBoxViewViewPreview() {
    GridBoxView(
        mutableListOf<WeatherIndex>(
            WeatherIndex(image = R.drawable.snow_moon, "Humidity", "60"),
            WeatherIndex(image = R.drawable.snow, "Wind", "10"),
            WeatherIndex(image = R.drawable.rainy_day, "Rainy", "20"),
            WeatherIndex(image = R.drawable.sun, "Sun", "20"),

            )
    )
}
