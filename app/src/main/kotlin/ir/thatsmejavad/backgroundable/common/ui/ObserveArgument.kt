package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun <T> NavController.ObserveArgument(
    key: String,
    onReceive: (T) -> Unit
) {
    currentBackStackEntryAsState().value
        ?.savedStateHandle
        ?.remove<T>(key)
        ?.let { item -> onReceive(item) }
}
