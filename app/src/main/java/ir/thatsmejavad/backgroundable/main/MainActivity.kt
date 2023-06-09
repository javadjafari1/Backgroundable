package ir.thatsmejavad.backgroundable.main

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
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
import ir.thatsmejavad.backgroundable.model.UserPreferences
import ir.thatsmejavad.backgroundable.ui.theme.BackgroundableTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
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
                val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle(
                    initialValue = UserPreferences()
                )
                BackgroundableTheme(
                    dynamicColor = userPreferences.isMaterialYouEnabled,
                    darkTheme = when (userPreferences.theme) {
                        Theme.FollowSystem -> isSystemInDarkTheme()
                        Theme.DarkTheme -> true
                        Theme.LightTheme -> false
                    }
                ) {
                    BackgroundableApp()
                }
            }
        }
    }
}

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
private fun BackgroundableApp() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val bottomSheetNavigator = remember { BottomSheetNavigator(sheetState) }
    val navController = rememberAnimatedNavController(bottomSheetNavigator)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        ModalBottomSheetLayout(
            bottomSheetNavigator = bottomSheetNavigator,
            sheetShape = MaterialTheme.shapes.large.copy(
                bottomEnd = CornerSize(0.dp),
                bottomStart = CornerSize(0.dp)
            ),
            sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = AppScreens.CollectionList.route
            ) {
                mainNavGraph(navController)
            }
        }

        /*
         * we have to add bottom bar like this, and not in Scaffold because
         * in scaffold we can't add animation for showing and hiding the bottomBar
         * */
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            AnimatedVisibility(
                visible = isMainScreen(navController.currentBackStackEntryAsState().value?.destination?.route),
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
            ) {
                BackgroundableNavigationBar(
                    selectedNavigationBarItem = when (navController.currentDestination?.route) {
                        AppScreens.Search.route -> SEARCH
                        AppScreens.Settings.route -> SETTING
                        else -> HOME
                    },
                    navigationBarDestinations = NavigationBarDestinations.values().toList(),
                    onItemSelected = { destinations ->
                        val route = when (destinations) {
                            HOME -> AppScreens.CollectionList
                            SEARCH -> AppScreens.Search
                            SETTING -> AppScreens.Settings
                        }.route

                        navController.navigate(route) {
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
    selectedNavigationBarItem: NavigationBarDestinations,
    navigationBarDestinations: List<NavigationBarDestinations>,
    onItemSelected: (NavigationBarDestinations) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.height(NAVIGATION_BAR_HEIGHT)
    ) {
        navigationBarDestinations.forEach { destination ->
            NavigationBarItem(
                selected = destination == selectedNavigationBarItem,
                onClick = { onItemSelected(destination) },
                alwaysShowLabel = false,
                icon = {
                    Crossfade(
                        targetState = selectedNavigationBarItem == destination,
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
                    Text(text = stringResource(destination.text))
                },
            )
        }
    }
}

private fun isMainScreen(route: String?): Boolean =
    route == AppScreens.CollectionList.route ||
        route == AppScreens.Search.route ||
        route == AppScreens.Settings.route
