package composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchPage(
    searchText: String,
    onSearchChanged: (String) -> Unit,
    onSearchClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { onSearchChanged(it) },
            label = { Text("Search") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f)
        )
        Button(
            onClick = { onSearchClick() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Text("Search")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FrontPagePreview() {
    SearchPage(searchText = "", onSearchChanged = {}, onSearchClick = {})}
