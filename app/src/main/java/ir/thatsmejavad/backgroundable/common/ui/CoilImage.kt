package ir.thatsmejavad.backgroundable.common.ui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun CoilImage(
    url: String,
    modifier: Modifier = Modifier,
    placeHolder: Painter? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = placeHolder,
        contentDescription = contentDescription ?: "a server image without content description",
        contentScale = contentScale,
    )
}

@Composable
fun CoilImage(
    url: String,
    modifier: Modifier = Modifier,
    placeHolder: Painter? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    isLoading: (Boolean) -> Unit = {},
    onDrawableLoaded: (Drawable) -> Unit = {},
) {
    val painter = rememberAsyncImagePainter(
        model = url,
        contentScale = contentScale,
        placeholder = placeHolder,
        onError = {
            isLoading(false)
        },
        onLoading = {
            isLoading(true)
        },
        onSuccess = {
            onDrawableLoaded(it.result.drawable)
            isLoading(false)
        }
    )
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription ?: "a server image without content description",
    )
}
