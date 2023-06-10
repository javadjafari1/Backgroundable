package ir.thatsmejavad.backgroundable.common.ui

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.saket.telephoto.zoomable.coil.ZoomableAsyncImage

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
fun ZoomableCoilImage(
    url: String,
    placeHolder: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    isLoading: (Boolean) -> Unit = {},
    onClick: ((Offset) -> Unit)? = null,
    onDrawableLoaded: (Drawable) -> Unit = {},
) {
    ZoomableAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .placeholderMemoryCacheKey(placeHolder)
            .data(url)
            .crossfade(2_000)
            .listener(
                onError = { _, _ ->
                    isLoading(false)
                },
                onStart = {
                    isLoading(true)
                },
                onSuccess = { _, it ->
                    onDrawableLoaded(it.drawable)
                    isLoading(false)
                }
            )
            .build(),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        onClick = onClick,
        contentDescription = contentDescription ?: "a server image without content description",
    )
}
