package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilImage(
    url: String,
    modifier: Modifier = Modifier,
    placeHolder: Painter? = null,
    contentDescription: String? = null,
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = placeHolder,
        contentDescription = contentDescription ?: "a server image without content description",
        contentScale = ContentScale.Crop,
    )
}
