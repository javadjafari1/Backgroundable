package ir.thatsmejavad.backgroundable.screens.featuredCollections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.thatsmejavad.backgroundable.core.AsyncJob
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.SnackbarMessage
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.model.Collection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeaturedCollectionsViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val _collections = MutableStateFlow<AsyncJob<List<Collection>>>(
        AsyncJob.Uninitialized
    )
    val collection: StateFlow<AsyncJob<List<Collection>>> = _collections

    init {
        getCollections()
    }

    fun getCollections() {
        viewModelScope.launch {
            try {
                _collections.emit(AsyncJob.Loading)
                _collections.emit(AsyncJob.Success(collectionRepository.getCollections().data))
            } catch (e: Exception) {
                _collections.emit(AsyncJob.Fail(e))
                snackbarManager.sendError(SnackbarMessage(e.message ?: ""))
            }
        }
    }
}
