package ir.thatsmejavad.backgroundable.screens.settings.themesetting

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.core.sealeds.ThemeColor
import ir.thatsmejavad.backgroundable.ui.theme.aoDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.aoLightColors
import ir.thatsmejavad.backgroundable.ui.theme.blueVioletDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.blueVioletLightColors
import ir.thatsmejavad.backgroundable.ui.theme.crayolaDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.crayolaLightColors
import ir.thatsmejavad.backgroundable.ui.theme.indigoDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.indigoLightColors
import ir.thatsmejavad.backgroundable.ui.theme.middleRedDarkColors
import ir.thatsmejavad.backgroundable.ui.theme.middleRedLightColors
import ir.thatsmejavad.backgroundable.ui.theme.skobeloffDarkColor
import ir.thatsmejavad.backgroundable.ui.theme.skobeloffLightColors
import kotlinx.coroutines.launch

@Composable
fun ThemeSettingScreen(
    viewModel: ThemeSettingViewModel,
    onBackClicked: () -> Unit,
) {
    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    BackgroundableScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
                },
                scrollBehavior = scrollBehavior
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
                derivedStateOf { tabs.indexOf(userPreferences.theme) }
            }

            TabRow(
                modifier = Modifier
                    .padding(top = 16.dp)
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

            Spacer(modifier = Modifier.size(32.dp))

            MaterialYouRow(
                isChecked = userPreferences.isMaterialYouEnabled,
                onCheckChanged = {
                    viewModel.updateIsMaterialYouEnabled(it)
                }
            )

            Spacer(modifier = Modifier.size(32.dp))

            ThemeRow(
                enabled = !userPreferences.isMaterialYouEnabled,
                isDarkTheme = when (userPreferences.theme) {
                    Theme.DarkTheme -> true
                    Theme.FollowSystem -> isSystemInDarkTheme()
                    Theme.LightTheme -> false
                },
                currentTheme = userPreferences.themeColor,
                onChangeTheme = { color ->
                    viewModel.updateThemeColor(color)
                }
            )
        }
    }
}

@Composable
private fun ColumnScope.ThemeRow(
    enabled: Boolean,
    isDarkTheme: Boolean,
    currentTheme: ThemeColor,
    onChangeTheme: (ThemeColor) -> Unit
) {

    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .align(Alignment.Start),
        text = stringResource(R.string.label_theme),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.size(24.dp))

    val alpha by animateFloatAsState(
        targetValue = (if (enabled) 1f else 0.2f),
        label = "alpha-anim"
    )
    LazyRow(
        modifier = Modifier.alpha(alpha),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(ThemeColor.items) { color ->
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = MaterialTheme.shapes.small
                    )
                    .clip(MaterialTheme.shapes.small)
                    .clickable(enabled = enabled) { onChangeTheme(color) }
                    .padding(12.dp)
                    .background(
                        color = if (isDarkTheme) {
                            when (color) {
                                ThemeColor.Ao -> aoDarkColors.primary
                                ThemeColor.BlueViolet -> blueVioletDarkColors.primary
                                ThemeColor.MiddleRed -> middleRedDarkColors.primary
                                ThemeColor.Skobeloff -> skobeloffDarkColor.primary
                                ThemeColor.Crayola -> crayolaDarkColors.primary
                                ThemeColor.Indigo -> indigoDarkColors.primary
                            }
                        } else {
                            when (color) {
                                ThemeColor.Ao -> aoLightColors.primary
                                ThemeColor.BlueViolet -> blueVioletLightColors.primary
                                ThemeColor.MiddleRed -> middleRedLightColors.primary
                                ThemeColor.Skobeloff -> skobeloffLightColors.primary
                                ThemeColor.Crayola -> crayolaLightColors.primary
                                ThemeColor.Indigo -> indigoLightColors.primary
                            }
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (currentTheme == color) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "selected-theme",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
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
        Column {
            Text(
                text = stringResource(R.string.label_material_you),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.label_material_you_desc),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surfaceTint
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Switch(checked = isChecked, onCheckedChange = onCheckChanged)
    }
}
