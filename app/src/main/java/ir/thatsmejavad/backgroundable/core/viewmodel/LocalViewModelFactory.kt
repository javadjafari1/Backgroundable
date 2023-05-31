package ir.thatsmejavad.backgroundable.core.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf

object LocalViewModelFactory {
    private val Factory = compositionLocalOf<ViewModelFactory?> { null }

    val current: ViewModelFactory?
        @Composable
        get() = Factory.current

    infix fun provides(viewModelFactory: ViewModelFactory): ProvidedValue<ViewModelFactory?> {
        return Factory.provides(viewModelFactory)
    }
}
