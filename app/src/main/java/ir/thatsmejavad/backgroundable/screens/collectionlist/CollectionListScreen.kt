package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CollectionListScreen(
    onImageClicked: () -> Unit,
) {
    Column {
        Text(text = "Page CollectionListScreen")
        Button(onClick = onImageClicked) {
            Text(text = "Open Image")
        }
    }
}