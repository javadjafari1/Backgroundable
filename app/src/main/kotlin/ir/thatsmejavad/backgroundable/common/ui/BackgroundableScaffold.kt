package ir.thatsmejavad.backgroundable.common.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.SnackbarMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundableScaffold(
    snackbarManager: SnackbarManager,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable ColumnScope.() -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(snackbarManager.messageSharedFlow) {
        snackbarManager
            .messageSharedFlow
            .onEach { snackbar ->
                snackbarHostState.showSnackbar(
                    message = snackbar.getErrorMessage(context),
                    duration = SnackbarDuration.Long
                )
            }
            .launchIn(this)
    }

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = {
            Column(
                modifier = contentModifier.padding(it),
                content = content
            )
        }
    )
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
