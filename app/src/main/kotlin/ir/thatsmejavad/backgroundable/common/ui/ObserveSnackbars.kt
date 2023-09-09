package ir.thatsmejavad.backgroundable.common.ui

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.SnackbarMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SnackbarManager.ObserveSnackbars(snackbarHostState: SnackbarHostState) {
    val context = LocalContext.current

    LaunchedEffect(messageSharedFlow) {
        messageSharedFlow
            .onEach { snackbar ->
                snackbarHostState.showSnackbar(
                    message = snackbar.getErrorMessage(context),
                    duration = SnackbarDuration.Long
                )
            }
            .launchIn(this)
    }
}

private fun SnackbarMessage.getErrorMessage(
    context: Context
): String {
    return when (message) {
        is String -> message
        is Int -> context.getString(message)
        else -> context.getString(R.string.unexpected_error_message)
    }
}
