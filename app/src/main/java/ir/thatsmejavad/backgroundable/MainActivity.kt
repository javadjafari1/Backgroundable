package ir.thatsmejavad.backgroundable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListScreen
import ir.thatsmejavad.backgroundable.screens.featuredCollections.FeaturedCollectionsScreen
import ir.thatsmejavad.backgroundable.screens.imagedetail.ImageDetailScreen
import ir.thatsmejavad.backgroundable.ui.theme.BackgroundableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            BackgroundableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "featured-collections"
                    ) {
                        mainNavGraph(navController)
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
        composable("featured-collections") {
            FeaturedCollectionsScreen(
                onCollectionClicked = {
                    navController.navigate("collection-list")
                }
            )
        }
        composable("collection-list") {
            CollectionListScreen(
                onImageClicked = {
                    navController.navigate("image-detail")
                }
            )
        }
        composable("image-detail") {
            ImageDetailScreen()
        }
    }
}
