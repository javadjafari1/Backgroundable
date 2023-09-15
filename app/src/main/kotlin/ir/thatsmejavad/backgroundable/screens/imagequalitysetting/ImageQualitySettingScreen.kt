package ir.thatsmejavad.backgroundable.screens.imagequalitysetting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality

@Composable
fun ImageQualitySettingScreen(
    viewModel: ImageQualitySettingViewModel,
    onBackClicked: () -> Unit
) {
    val imageQuality by viewModel.imageQuality.collectAsStateWithLifecycle()

    BackgroundableScaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(R.string.label_image_quality))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                ImageQuality.items.forEachIndexed { index, quality ->
                    SegmentedButton(
                        shape = when (index) {
                            0 -> {
                                MaterialTheme.shapes.large.copy(
                                    topEnd = CornerSize(0.dp),
                                    bottomEnd = CornerSize(0.dp)
                                )
                            }

                            ImageQuality.items.lastIndex -> {
                                MaterialTheme.shapes.large.copy(
                                    topStart = CornerSize(0.dp),
                                    bottomStart = CornerSize(0.dp)
                                )
                            }

                            else -> RectangleShape
                        },
                        selected = imageQuality == quality,
                        onClick = { viewModel.setImageQuality(quality) }
                    ) {
                        Text(text = quality.toString())
                    }
                }
            }

            Text(
                text = LoremIpsum(50).values.joinToString(),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
