package ir.thatsmejavad.backgroundable.screens.collectionlist

import android.util.Log
import androidx.lifecycle.ViewModel
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import javax.inject.Inject

class CollectionListViewModel @Inject constructor(
    collectionRepository: CollectionRepository,
) : ViewModel() {

    init {
        Log.d("Jai", " CollectionListVIewModel: init")
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("Jai", " CollectionListViewModel: cleared")
    }
}
