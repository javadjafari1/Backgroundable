package ir.thatsmejavad.backgroundable.screens.downloadpicker

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.getErrorMessage
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.sealeds.OrientationMode

@Composable
fun DownloadPickerScreen(
    viewModel: DownloadPickerViewModel,
    navigateBack: () -> Unit
) {
    val mediaResult by viewModel.media.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (mediaResult) {
            is AsyncJob.Success -> {
                val mediaMap by remember {
                    derivedStateOf {
                        (mediaResult as AsyncJob.Success).value.resources.groupBy { it.size is OrientationMode }
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(
                                top = 20.dp,
                                bottom = 12.dp,
                                start = 32.dp,
                                end = 32.dp
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

            is AsyncJob.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            is AsyncJob.Fail -> {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = (mediaResult as AsyncJob.Fail)
                        .exception
                        .getErrorMessage()
                        .asString()
                )
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedButton(
                    modifier = Modifier.padding(16.dp),
                    onClick = { viewModel.getMedia() }
                ) {
                    Text(text = stringResource(R.string.label_try_again))
                }
            }

            is AsyncJob.Uninitialized -> {}
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
            .padding(horizontal = 16.dp)
            .clip(MaterialTheme.shapes.extraSmall)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        text = name,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyLarge
    )
}
