package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun <T> NavController.ObserveArgument(
    key: String,
    onReceive: (T) -> Unit
) {
    val backStack by currentBackStackEntryAsState()
    backStack?.let {
        it.savedStateHandle.remove<T>(key)?.let { item ->
            onReceive(item)
        }
    }
}
