package ir.thatsmejavad.backgroundable.screens.featuredCollections

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun FeaturedCollectionsScreen(
    onCollectionClicked: () -> Unit,
) {
    Column {
        Text(text = "Page FeaturedCollectionsScreen")
        Button(onClick = onCollectionClicked) {
            Text(text = "Open Collection")
        }
    }
}