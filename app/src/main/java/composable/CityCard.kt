package composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CityCard(
    city: String,
    temperature: Double,
    onCardClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = onCardClick,
            ),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // Light blue background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = city,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${temperature}°C",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = when {
                    temperature >= 30 -> Color(0xFFD84315)
                    temperature >= 15 -> Color(0xFF2E7D32)
                    else -> Color(0xFF1565C0)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityCardPreview() {
    CityCard(
        city = "Kitchener",
        temperature = 25.5,
        onCardClick = {}
    )
}
