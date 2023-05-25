package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilImage(
    url: String,
    contentDescription: String? = null,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        // placeholder = painterResource(R.drawable.placeholder),
        contentDescription = contentDescription ?: "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
    )
}
