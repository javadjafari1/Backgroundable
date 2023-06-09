package ir.thatsmejavad.backgroundable.screens.mediadetail

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.CircularLoading
import ir.thatsmejavad.backgroundable.common.ui.ZoomableCoilImage
import ir.thatsmejavad.backgroundable.core.getStringMessage
import ir.thatsmejavad.backgroundable.core.getUri
import ir.thatsmejavad.backgroundable.core.saveIn
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.core.setWallpaperWithImage
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
    navigateToDownloadPicker: () -> Unit,
) {
    val mediaResult by viewModel.media.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var isToolsVisible by rememberSaveable { mutableStateOf(true) }
    var isImageLoading by rememberSaveable { mutableStateOf(false) }

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
    ) {
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

            is AsyncJob.Success -> {
                val mediaWithResources = (mediaResult as AsyncJob.Success).value
                var drawable by remember { mutableStateOf<Drawable?>(null) }
                val scope = rememberCoroutineScope()

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AnimatedVisibility(isImageLoading) {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                    ZoomableCoilImage(
                        modifier = Modifier.weight(1f),
                        onClick = { isToolsVisible = !isToolsVisible },
                        url = mediaWithResources.resources.first { it.size == ResourceSize.Original }.url,
                        placeHolder = mediaWithResources.resources.first { it.size == ResourceSize.Medium }.url,
                        contentDescription = mediaWithResources.media.alt,
                        isLoading = { isImageLoading = it },
                        onDrawableLoaded = { drawable = it }
                    )
                    AnimatedVisibility(visible = isToolsVisible && !isImageLoading) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Button(
                                modifier = Modifier
                                    .height(56.dp)
                                    .weight(1f),
                                onClick = {
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
                                },
                                shape = MaterialTheme.shapes.large,
                            ) {
                                Text(
                                    text = stringResource(R.string.label_set_as_wallpaper)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            FilledIconButton(
                                modifier = Modifier.size(56.dp),
                                shape = MaterialTheme.shapes.large,
                                onClick = {
                                    navigateToDownloadPicker()
                                },
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_download),
                                    contentDescription = "Download",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
