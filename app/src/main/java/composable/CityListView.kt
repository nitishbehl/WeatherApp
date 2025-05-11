package composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import model.CityResponse

@Composable
fun CityListView(cities: List<CityResponse.City?>?, onCityClicked: (String) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF929CDE))
    ) {
        if (cities != null) {
            items(cities.size) { index ->
                val city = cities[index]

                if (city != null) {
                    CityCard(
                        city = city.city,
                        condition = city.condition.orEmpty(),
                        datetime = city.datetime.orEmpty(),
                        humidity = city.humidity.orEmpty(),
                        temperature = city.temperature?.toDoubleOrNull() ?: 0.0,
                        onCityClicked = onCityClicked
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CityListPreview() {
    val cities = listOf(
        CityResponse.City("Toronto", "Cloudy", "Mon, 10:15 AM", "65%", "22.5"),
        CityResponse.City("Halifax", "Windy", "Mon, 12:30 PM", "60%", "14.7")
    )
    CityListView(cities) { city ->

    }

}
