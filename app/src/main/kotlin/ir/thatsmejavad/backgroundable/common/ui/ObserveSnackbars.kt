package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SnackbarManager.ObserveSnackbars(snackbarHostState: SnackbarHostState) {
    val context = LocalContext.current

    LaunchedEffect(messageSharedFlow) {
        messageSharedFlow
            .onEach { snackbar ->
                snackbarHostState.showSnackbar(
                    message = snackbar.message.asString(context),
                    duration = SnackbarDuration.Long
                )
            }
            .launchIn(this)
    }
}
