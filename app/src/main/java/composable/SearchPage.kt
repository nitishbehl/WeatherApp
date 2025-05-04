package composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchPage(
    searchText: String,
    onSearchChanged: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { onSearchChanged.invoke(it) },
            label = { Text("Search") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun FrontPagePreview() {
    SearchPage(
        searchText = ""

    ) { city -> }
}
