package ir.thatsmejavad.backgroundable.core.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun Inject(
    viewModelFactory: ViewModelFactory,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalViewModelFactory provides viewModelFactory,
        content = content
    )
}
