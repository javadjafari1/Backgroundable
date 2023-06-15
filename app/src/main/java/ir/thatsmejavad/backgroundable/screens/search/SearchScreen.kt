package ir.thatsmejavad.backgroundable.screens.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
) {
    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        modifier = Modifier
            /*
            The padding of the bottomBar,
            can't use Scaffold to add bottomBar with animation.
            the bottom of the ui will jump
            */
            .padding(bottom = NAVIGATION_BAR_HEIGHT),
    ) {
        Text(
            text = "Search"
        )
    }
}
