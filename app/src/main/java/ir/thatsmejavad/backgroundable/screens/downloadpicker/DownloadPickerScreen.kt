package ir.thatsmejavad.backgroundable.screens.downloadpicker

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.sealeds.OrientationMode

@Composable
fun DownloadPickerScreen(
    viewModel: DownloadPickerViewModel,
    navigateBack: () -> Unit
) {
    val mediaResult by viewModel.media.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (mediaResult is AsyncJob.Success) {
        val mediaMap = remember {
            (mediaResult as AsyncJob.Success).value.resources.groupBy { it.size is OrientationMode }
        }
        LazyColumn {
            item {
                Text(
                    modifier = Modifier.padding(
                        vertical = 20.dp,
                        horizontal = 32.dp
                    ),
                    text = stringResource(R.string.label_download_wallpaper),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                HorizontalDivider()
            }
            mediaMap[false]?.let { resources ->
                items(resources) { resourceEntity ->
                    DownloadItem(
                        name = resourceEntity.size.size.replaceFirstChar { it.uppercaseChar() },
                        onClick = {
                            viewModel.download(resourceEntity)
                            navigateBack()
                            Toast.makeText(
                                context,
                                R.string.label_download_is_about_to_begin,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            }
            item {
                HorizontalDivider()
            }
            mediaMap[true]?.let { resources ->
                items(resources) { resourceEntity ->
                    DownloadItem(
                        name = resourceEntity.size.size.replaceFirstChar { it.uppercaseChar() },
                        onClick = {
                            viewModel.download(resourceEntity)
                            navigateBack()
                            Toast.makeText(
                                context,
                                R.string.label_download_is_about_to_begin,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DownloadItem(
    name: String,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clip(MaterialTheme.shapes.extraSmall)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        text = name,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyLarge
    )
}
