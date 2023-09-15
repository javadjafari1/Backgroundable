package ir.thatsmejavad.backgroundable.screens.mediadetail

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.core.getUri
import ir.thatsmejavad.backgroundable.core.saveIn
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MediaDetailViewModel @AssistedInject constructor(
    val snackbarManager: SnackbarManager,
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<MediaDetailViewModel>

    var savePurpose: SavePurpose = SavePurpose.SettingWallpaper
        private set

    private val _media = MutableStateFlow<AsyncJob<MediaWithResources>>(
        AsyncJob.Uninitialized
    )
    val media: StateFlow<AsyncJob<MediaWithResources>> = _media.asStateFlow()

    private val _fileUri = MutableStateFlow<AsyncJob<Uri>>(
        AsyncJob.Uninitialized
    )

    val fileUri: StateFlow<AsyncJob<Uri>> = _fileUri.asStateFlow()

    init {
        val id = checkNotNull(savedStateHandle.get<Int>("id")) {
            "id should not be null in $this"
        }
        getMedia(id)
    }

    fun getMedia(mediaId: Int) {
        viewModelScope.launch {
            _media.emit(AsyncJob.Uninitialized)
            try {
                mediaRepository.getMediaWithResources(mediaId)?.let { result ->
                    _media.emit(AsyncJob.Success(result))
                }
            } catch (e: Exception) {
                _media.emit(AsyncJob.Fail(e))
            }
        }
    }

    fun saveFile(
        purpose: SavePurpose,
        drawable: Drawable,
        context: Context
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            savePurpose = purpose
            _fileUri.emit(AsyncJob.Loading)
            try {
                val uri = drawable
                    .toBitmap()
                    .saveIn(context.cacheDir)
                    .getUri(context)

                _fileUri.emit(AsyncJob.Success(uri))
            } catch (e: Exception) {
                _fileUri.emit(AsyncJob.Fail(e))
                snackbarManager.sendError(e.getSnackbarMessage())
            }
        }
    }
}

sealed interface SavePurpose {
    data object SettingWallpaper : SavePurpose
    data object Share : SavePurpose
}

