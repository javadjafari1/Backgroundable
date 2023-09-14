package ir.thatsmejavad.backgroundable.screens.themesetting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import kotlinx.coroutines.launch

@Composable
fun ThemeSettingScreen(
    viewModel: ThemeSettingViewModel,
    onBackClicked: () -> Unit,
) {
    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    BackgroundableScaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(R.string.label_theme))
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
                .verticalScroll(rememberScrollState()),
        ) {
            val tabs = listOf(
                Theme.LightTheme,
                Theme.DarkTheme,
                Theme.FollowSystem
            )
            val selectedTabIndex by remember(userPreferences.theme) {
                mutableIntStateOf(
                    tabs.indexOf(
                        userPreferences.theme
                    )
                )
            }

            TabRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.extraLarge),
                selectedTabIndex = selectedTabIndex,
                divider = {},
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .fillMaxHeight()
                            .clip(MaterialTheme.shapes.extraLarge)
                            .padding(10.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.extraLarge
                            )
                            .zIndex(-1f)
                    )
                },
                tabs = {
                    tabs.forEachIndexed { index, theme ->
                        Tab(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clip(MaterialTheme.shapes.extraLarge),
                            selected = selectedTabIndex == index,
                            selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            onClick = { scope.launch { viewModel.updateTheme(theme) } },
                            text = {
                                Text(
                                    text = stringResource(
                                        when (theme) {
                                            Theme.DarkTheme -> R.string.label_dark
                                            Theme.FollowSystem -> R.string.label_follow_system
                                            Theme.LightTheme -> R.string.label_light
                                        }
                                    ),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            MaterialYouRow(
                isChecked = userPreferences.isMaterialYouEnabled,
                onCheckChanged = {
                    viewModel.updateIsMaterialYouEnabled(it)
                }
            )
        }
    }
}

@Composable
private fun MaterialYouRow(
    isChecked: Boolean,
    onCheckChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onCheckChanged(!isChecked) }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.label_material_you),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))

        Switch(checked = isChecked, onCheckedChange = onCheckChanged)
    }
}
