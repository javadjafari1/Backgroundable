package ir.thatsmejavad.backgroundable.screens.medialist

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MediaListViewModel @AssistedInject constructor(
    val snackbarManager: SnackbarManager,
    private val mediaRepository: MediaRepository,
    settingRepository: SettingRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<MediaListViewModel>

    private val _medias =
        MutableStateFlow<PagingData<MediaWithResources>>(value = PagingData.empty())
    val medias: StateFlow<PagingData<MediaWithResources>> = _medias.asStateFlow()

    val mediaColumnTypeFlow = mediaRepository.mediaColumnTypeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = List.StaggeredType
    )

    val imageQuality = settingRepository.userPreferencesFlow
        .map { it.imageQuality }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UserPreferences().imageQuality
        )

    init {
        val id = requireNotNull(savedStateHandle.get<String>("id")) {
            "id should not be null in $this"
        }
        getMedias(id)
    }

    private fun getMedias(id: String) {
        mediaRepository
            .getMediasByCollectionId(id)
            .cachedIn(viewModelScope)
            .onEach {
                _medias.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun changeColumnCount() {
        viewModelScope.launch {
            val type = getNewColumnTypeByCurrentType(mediaColumnTypeFlow.first())
            mediaRepository.setMediaColumnType(type)
        }
    }

    @VisibleForTesting
    fun getNewColumnTypeByCurrentType(list: List): List {
        return when (list) {
            List.GridType -> List.ListType
            List.ListType -> List.StaggeredType
            List.StaggeredType -> List.GridType
        }
    }
}
