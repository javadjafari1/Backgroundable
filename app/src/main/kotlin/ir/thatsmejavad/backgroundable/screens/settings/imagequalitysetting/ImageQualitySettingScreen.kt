package ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality.Companion.toResId
import ir.thatsmejavad.backgroundable.core.viewmodel.daggerViewModel

@Composable
fun ImageQualitySettingScreen(
    navController: NavController,
    viewModel: ImageQualitySettingViewModel = daggerViewModel()
) {
    val imageQuality by viewModel.imageQuality.collectAsStateWithLifecycle()

    ImageQualitySettingScreen(
        imageQuality = imageQuality,
        onBackClicked = { navController.navigateUp() },
        changeQuality = { viewModel.setImageQuality(it) }
    )
}

@Composable
private fun ImageQualitySettingScreen(
    imageQuality: ImageQuality,
    onBackClicked: () -> Unit,
    changeQuality: (ImageQuality) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    BackgroundableScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(R.string.label_quality))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                ImageQuality.items.forEachIndexed { index, quality ->
                    SegmentedButton(
                        shape = when (index) {
                            0 -> {
                                MaterialTheme.shapes.extraLarge.copy(
                                    topEnd = CornerSize(0.dp),
                                    bottomEnd = CornerSize(0.dp)
                                )
                            }

                            ImageQuality.items.lastIndex -> {
                                MaterialTheme.shapes.extraLarge.copy(
                                    topStart = CornerSize(0.dp),
                                    bottomStart = CornerSize(0.dp)
                                )
                            }

                            else -> RectangleShape
                        },
                        selected = imageQuality == quality,
                        onClick = { changeQuality(quality) }
                    ) {
                        Text(text = stringResource(quality.toResId()))
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    .padding(all = 10.dp),
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.ic_help),
                    contentDescription = "help",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.size(4.dp))

                val lowText = buildAnnotatedString {
                    val low = "${stringResource(R.string.label_low)}: "
                    val lowDesc = stringResource(R.string.label_low_desc)
                    append(low)
                    append(lowDesc)

                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = low.length,
                        end = low.length + lowDesc.length
                    )
                }

                Text(
                    text = lowText,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )

                val mediumText = buildAnnotatedString {
                    val medium = "${stringResource(R.string.label_medium)}: "
                    val mediumDesc = stringResource(R.string.label_medium_desc)
                    append(medium)
                    append(mediumDesc)

                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = medium.length,
                        end = medium.length + mediumDesc.length
                    )
                }

                Text(
                    text = mediumText,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )

                val highText = buildAnnotatedString {
                    val high = "${stringResource(R.string.label_high)}: "
                    val highDesc = stringResource(R.string.label_high_desc)
                    append(high)
                    append(highDesc)

                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = high.length,
                        end = high.length + highDesc.length
                    )
                }

                Text(
                    text = highText,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
                val ultraText = buildAnnotatedString {
                    val ultra = "${stringResource(R.string.label_ultra)}: "
                    val ultraDesc = stringResource(R.string.label_ultra_desc)
                    append(ultra)
                    append(ultraDesc)

                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = ultra.length,
                        end = ultra.length + ultraDesc.length
                    )
                }

                Text(
                    text = ultraText,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Text(
                text = stringResource(R.string.label_image_quality_desc),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
