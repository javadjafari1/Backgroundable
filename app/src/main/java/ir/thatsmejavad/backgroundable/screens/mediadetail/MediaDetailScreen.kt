package ir.thatsmejavad.backgroundable.screens.mediadetail

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.CircularLoading
import ir.thatsmejavad.backgroundable.common.ui.CoilImage
import ir.thatsmejavad.backgroundable.core.AsyncJob
import ir.thatsmejavad.backgroundable.core.getStringMessage
import ir.thatsmejavad.backgroundable.core.getUri
import ir.thatsmejavad.backgroundable.core.saveIn
import ir.thatsmejavad.backgroundable.core.setWallpaperWithImage
import ir.thatsmejavad.backgroundable.core.toColor
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailScreen(
    mediaId: Int,
    title: String,
    viewModel: MediaDetailViewModel,
    onBackClicked: () -> Unit,
) {
    val mediaResult by viewModel.media.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var isToolsVisible by remember { mutableStateOf(true) }

    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        topBar = {
            AnimatedVisibility(
                visible = isToolsVisible && title.isNotEmpty(),
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                LargeTopAppBar(
                    title = {
                        Text(text = title)
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClicked) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    },
                )
            }
        },
    ) { paddingValues ->
        when (mediaResult) {
            is AsyncJob.Fail -> {
                Text(text = (mediaResult as AsyncJob.Fail).exception.getStringMessage(context))
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedButton(onClick = { viewModel.getMedia(mediaId) }) {
                    Text(text = stringResource(R.string.label_try_again))
                }
            }

            AsyncJob.Loading, AsyncJob.Uninitialized -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularLoading()
                }
            }

            is AsyncJob.Success<Media> -> {
                val media = (mediaResult as AsyncJob.Success).value
                var isLoading by rememberSaveable { mutableStateOf(true) }
                var drawable by remember { mutableStateOf<Drawable?>(null) }
                val scope = rememberCoroutineScope()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ZoomableBox(
                        Modifier.weight(1f),
                        onClick = { isToolsVisible = !isToolsVisible },
                    ) {
                        CoilImage(
                            url = media.resources.original,
                            contentDescription = media.alt,
                            placeHolder = ColorPainter(media.avgColor.toColor()),
                            isLoading = { isLoading = it },
                            onDrawableLoaded = { drawable = it }
                        )
                        if (isLoading) {
                            CircularLoading()
                        }
                    }
                    AnimatedVisibility(visible = isToolsVisible && !isLoading) {
                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.extraLarge)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .clickable {
                                    scope.launch {
                                        withContext(Dispatchers.IO) {
                                            drawable
                                                .takeIf { it != null }
                                                ?.let {
                                                    val uri = it
                                                        .toBitmap()
                                                        .saveIn(context.cacheDir)
                                                        .getUri(context)
                                                    (context as Activity).setWallpaperWithImage(
                                                        uri = uri,
                                                        onError = {}
                                                    )
                                                }
                                        }
                                    }
                                }
                                .padding(vertical = 24.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.label_set_as_wallpaper)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ZoomableBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        offset += offsetChange
    }

    Box(
        modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offset.x,
                translationY = offset.y
            )
            .transformable(state = state)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onDoubleClick = {
                    scale = 1f
                    offset = Offset.Zero
                },
                onClick = onClick
            ),
        contentAlignment = contentAlignment,
        content = content
    )
}
