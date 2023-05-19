package ir.thatsmejavad.backgroundable.screens.featuredCollections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _collections = MutableStateFlow<List<Collection>>(listOf())
    val collection: StateFlow<List<Collection>> = _collections

    init {
        getCollections()
    }

    private fun getCollections() {
        viewModelScope.launch {
            try {
                _collections.emit(collectionRepository.getCollections().data)
            } catch (e: Exception) {
                snackbarManager.sendError(SnackbarMessage(e.message ?: ""))
            }
        }
    }
}
