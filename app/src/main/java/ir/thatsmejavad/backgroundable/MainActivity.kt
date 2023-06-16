package ir.thatsmejavad.backgroundable

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.thatsmejavad.backgroundable.common.ui.NavigationBarDestinations
import ir.thatsmejavad.backgroundable.common.ui.NavigationBarDestinations.HOME
import ir.thatsmejavad.backgroundable.common.ui.NavigationBarDestinations.SEARCH
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT
import ir.thatsmejavad.backgroundable.core.viewmodel.LocalViewModelFactory
import ir.thatsmejavad.backgroundable.ui.theme.BackgroundableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backgroundableApplication = (application as BackgroundableApplication)

        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && BuildConfig.DEBUG) {
                val requestPermissionLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}
                SideEffect {
                    requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            BackgroundableTheme {
                CompositionLocalProvider(
                    LocalViewModelFactory provides backgroundableApplication.appComponent.getViewModelFactory()
                ) {
                    BackgroundableApp()
                }
            }
        }
    }
}

@Composable
private fun BackgroundableApp() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.CollectionList.route
        ) {
            mainNavGraph(navController)
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            AnimatedVisibility(
                visible = isMainScreen(navController.currentBackStackEntryAsState().value?.destination?.route),
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
            ) {
                BackgroundableNavigationBar(
                    selectedNavigationBarItem = if (navController.currentDestination?.route == AppScreens.Search.route) {
                        SEARCH
                    } else {
                        HOME
                    },
                    navigationBarDestinations = NavigationBarDestinations.values().toList(),
                    onItemSelected = { destinations ->
                        val route = when (destinations) {
                            HOME -> AppScreens.CollectionList
                            SEARCH -> AppScreens.Search
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
                alwaysShowLabel = false,
            )
        }
    }
}

private fun isMainScreen(route: String?): Boolean = route == AppScreens.CollectionList.route ||
    route == AppScreens.Search.route
