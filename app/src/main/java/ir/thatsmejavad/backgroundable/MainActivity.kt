package ir.thatsmejavad.backgroundable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ir.thatsmejavad.backgroundable.core.viewmodel.Inject
import ir.thatsmejavad.backgroundable.ui.theme.BackgroundableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            BackgroundableTheme {
                Inject(App.app.getViewModelFactory()) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = AppScreens.FeaturedCollections.route
                        ) {
                            mainNavGraph(navController)
                        }
                    }
                }
            }
        }
    }
}
