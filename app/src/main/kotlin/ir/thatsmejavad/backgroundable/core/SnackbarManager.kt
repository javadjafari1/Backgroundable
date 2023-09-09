package ir.thatsmejavad.backgroundable.core

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackbarManager @Inject constructor() {

    private val channel = Channel<SnackbarMessage>(capacity = 5, BufferOverflow.DROP_OLDEST)

    val messageSharedFlow: Flow<SnackbarMessage> = channel.receiveAsFlow()

    suspend fun sendError(snackbarMessage: SnackbarMessage) {
        channel.send(snackbarMessage)
    }
}
