package ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
                    Text(text = stringResource(R.string.label_image_quality))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
                        Text(text = quality.toString())
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

                val text = buildAnnotatedString {
                    append(stringResource(R.string.label_low_desc))
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        start = 0,
                        end = 4
                    )
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = 4,
                        end = 46
                    )
                    appendLine()
                    append(stringResource(R.string.label_medium_desc))
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        start = 49,
                        end = 56
                    )
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = 56,
                        end = 95
                    )
                    appendLine()
                    append(stringResource(R.string.label_high_desc))
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        start = 96,
                        end = 101
                    )
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = 101,
                        end = 138
                    )
                    appendLine()
                    append(stringResource(R.string.label_ultra_desc))
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        start = 139,
                        end = 145
                    )
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.surfaceTint,
                        ),
                        start = 146,
                        end = 175
                    )
                }
                Text(
                    text = text,
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
