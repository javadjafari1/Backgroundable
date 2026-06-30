package ir.thatsmejavad.backgroundable.common.ui

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import coil3.asDrawable
import coil3.compose.AsyncImage as AsyncImage3
import coil3.request.ImageRequest as ImageRequest3
import coil3.request.crossfade as crossfade3
import ir.thatsmejavad.backgroundable.BuildConfig
import me.saket.telephoto.zoomable.coil3.ZoomableAsyncImage

@Composable
fun CoilImage(
    url: String,
    modifier: Modifier = Modifier,
    placeHolder: Painter? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage3(
        modifier = modifier,
        model = ImageRequest3.Builder(LocalContext.current)
            .data(BuildConfig.IMAGE_SERVER_URL + url)
            .crossfade3(500)
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
    val context = LocalContext.current
    val resource = LocalResources.current

    ZoomableAsyncImage(
        modifier = modifier,
        model = ImageRequest3.Builder(context)
            .placeholderMemoryCacheKey(BuildConfig.IMAGE_SERVER_URL + placeHolder)
            .data(BuildConfig.IMAGE_SERVER_URL + url)
            .crossfade3(2_000)
            .listener(
                onError = { _, _ ->
                    isLoading(false)
                },
                onStart = {
                    isLoading(true)
                },
                onSuccess = { _, result ->
                    onDrawableLoaded(result.image.asDrawable(resource))
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
