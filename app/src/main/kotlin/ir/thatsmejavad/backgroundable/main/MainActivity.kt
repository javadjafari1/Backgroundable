package ir.thatsmejavad.backgroundable.main

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import ir.thatsmejavad.backgroundable.BackgroundableApplication
import ir.thatsmejavad.backgroundable.BuildConfig
import ir.thatsmejavad.backgroundable.common.ui.NavigationBarDestinations
import ir.thatsmejavad.backgroundable.common.ui.NavigationBarDestinations.HOME
import ir.thatsmejavad.backgroundable.common.ui.NavigationBarDestinations.SEARCH
import ir.thatsmejavad.backgroundable.common.ui.NavigationBarDestinations.SETTING
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.core.viewmodel.LocalViewModelFactory
import ir.thatsmejavad.backgroundable.mainNavGraph
import ir.thatsmejavad.backgroundable.settingNavGraph
import ir.thatsmejavad.backgroundable.ui.BackgroundableTheme
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val backgroundableApplication = (application as BackgroundableApplication)

        backgroundableApplication.appComponent.activityViewModelComponentBuilder()
            .componentActivity(this)
            .build()
            .inject(this)

        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && BuildConfig.DEBUG) {
                val requestPermissionLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}
                SideEffect {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            CompositionLocalProvider(
                LocalViewModelFactory provides backgroundableApplication.appComponent.getViewModelFactory()
            ) {
                val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle()
                BackgroundableTheme(
                    themeColor = userPreferences.themeColor,
                    dynamicColor = userPreferences.isMaterialYouEnabled,
                    darkTheme = when (userPreferences.theme) {
                        Theme.FollowSystem -> isSystemInDarkTheme()
                        Theme.DarkTheme -> true
                        Theme.LightTheme -> false
                    },
                ) {
                    val isDark = when (userPreferences.theme) {
                        Theme.DarkTheme -> true
                        Theme.FollowSystem -> isSystemInDarkTheme()
                        Theme.LightTheme -> false
                    }
                    LaunchedEffect(isDark) {
                        setSystemBarsColor(isDark)
                    }
                    BackgroundableApp()
                }
            }
        }
    }
}

@Composable
private fun BackgroundableApp() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = MaterialTheme.shapes.large.copy(
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        ),
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
    ) {
        Box {
            NavHost(
                navController = navController,
                startDestination = AppScreens.CollectionList.route
            ) {
                mainNavGraph(navController)
                settingNavGraph(navController)
            }
            /*
             * we have to add bottom bar like this, and not in Scaffold because
             * in scaffold we can't add animation for showing and hiding the bottomBar
             * */
            val backStackEntry by navController.currentBackStackEntryAsState()
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = backStackEntry?.destination?.route in NavigationBarDestinations.entries.map { it.route },
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
            ) {
                BackgroundableNavigationBar(
                    selectedItem = when (navController.currentDestination?.route) {
                        AppScreens.Search.route -> SEARCH
                        AppScreens.Settings.route -> SETTING
                        else -> HOME
                    },
                    onItemSelected = { destinations ->
                        navController.navigate(destinations.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BackgroundableNavigationBar(
    selectedItem: NavigationBarDestinations,
    onItemSelected: (NavigationBarDestinations) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.heightIn(min = NAVIGATION_BAR_HEIGHT),
        containerColor = MaterialTheme.colorScheme.surfaceBright,
    ) {
        NavigationBarDestinations.entries.forEach { destination ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                selected = destination == selectedItem,
                onClick = { onItemSelected(destination) },
                alwaysShowLabel = false,
                icon = {
                    Crossfade(
                        targetState = selectedItem == destination,
                        label = "navigation items animation"
                    ) { isSelected ->
                        Icon(
                            imageVector = if (isSelected) {
                                destination.selectedIcon
                            } else {
                                destination.unselectedIcon
                            },
                            contentDescription = "${stringResource(destination.text)}-navigation-item",
                        )
                    }
                },
                label = {
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = stringResource(destination.text),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1
                    )
                },
            )
        }
    }
}

internal fun AppCompatActivity.setSystemBarsColor(isDark: Boolean) {
    enableEdgeToEdge(
        statusBarStyle = if (isDark) {
            SystemBarStyle.dark(Color.Transparent.toArgb())
        } else {
            SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Black.copy(alpha = 0.3f).toArgb()
            )
        },
        navigationBarStyle = if (isDark) {
            SystemBarStyle.dark(Color.Transparent.toArgb())
        } else {
            SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Black.copy(alpha = 0.3f).toArgb()
            )
        }
    )
}
