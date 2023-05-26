package ir.thatsmejavad.backgroundable.screens.mediadetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.CircularLoading
import ir.thatsmejavad.backgroundable.common.ui.CoilImage
import ir.thatsmejavad.backgroundable.core.AsyncJob
import ir.thatsmejavad.backgroundable.core.getStringMessage
import ir.thatsmejavad.backgroundable.core.toColor
import ir.thatsmejavad.backgroundable.model.media.Media

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailScreen(
    mediaId: Int,
    viewModel: MediaDetailViewModel
) {

    val mediaResult by viewModel.media.collectAsStateWithLifecycle()
    val context = LocalContext.current

    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        topBar = {}
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

            is AsyncJob.Success<Media> -> {
                val media = (mediaResult as AsyncJob.Success).value
                var isLoading by remember { mutableStateOf(true) }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    CoilImage(
                        modifier = Modifier.fillMaxSize(),
                        url = media.resources.original,
                        contentDescription = media.alt,
                        placeHolder = ColorPainter(media.avgColor.toColor()),
                        isLoading = { isLoading = it },
                    )
                    if (isLoading) {
                        CircularLoading()
                    }
                }
            }
        }
    }
}
