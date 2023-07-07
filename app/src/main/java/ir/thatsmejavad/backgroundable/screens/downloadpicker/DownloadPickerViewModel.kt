package ir.thatsmejavad.backgroundable.screens.downloadpicker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DownloadPickerViewModel @AssistedInject constructor(
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<DownloadPickerViewModel>

    private val _media =
        MutableStateFlow<AsyncJob<MediaWithResources>>(AsyncJob.Uninitialized)
    val media: StateFlow<AsyncJob<MediaWithResources>> = _media

    init {
        val id = checkNotNull(savedStateHandle.get<Int>("id")) {
            "id should not be null in $this"
        }
        getMedia(id)
    }

    private fun getMedia(mediaId: Int) {
        viewModelScope.launch {
            _media.value = AsyncJob.Loading
            try {
                mediaRepository.getMediaWithResources(mediaId)?.let { result ->
                    _media.value = AsyncJob.Success(result)
                }
            } catch (e: Exception) {
                _media.value = AsyncJob.Fail(e)
            }
        }
    }

    fun download() {
//        try {
//            val resource =
//                mediaWithResources.resources.first { it.size == ResourceSize.Original }
//            val request =
//                DownloadManager.Request(Uri.parse(resource.url))
//                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//                    .setDestinationInExternalPublicDir(
//                        Environment.DIRECTORY_DOWNLOADS,
//                        "${mediaWithResources.media.alt} by ${mediaWithResources.media.photographer} - ${ResourceSize.Original.size}.jpeg"
//                    )
//                    .setTitle(mediaWithResources.media.alt)
//                    .setMimeType("image/jpeg")
//                    .setDescription("by ${mediaWithResources.media.photographer}")
//                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
//
//            val downloadManager =
//                ContextCompat.getSystemService(context, DownloadManager::class.java)
//            downloadManager!!.enqueue(request)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }
}