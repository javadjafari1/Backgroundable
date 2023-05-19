package ir.thatsmejavad.backgroundable.screens.imagedetail

import android.util.Log
import androidx.lifecycle.ViewModel
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    init {
        Log.d("Jai", " ImageDetailViewModel: init")
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("Jai", " ImageDetailViewModel: cleared")
    }
}
