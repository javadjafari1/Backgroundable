package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CollectionListScreen(
    onImageClicked: (String) -> Unit,
) {
    Column {
        Text(text = "Page CollectionListScreen")
        Button(onClick = { onImageClicked("1") }) {
            Text(text = "Open Image")
        }
    }
}
