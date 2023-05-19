package ir.thatsmejavad.backgroundable.screens.featuredCollections

import android.util.Log
import androidx.lifecycle.ViewModel
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import javax.inject.Inject

class FeaturedCollectionsViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
) : ViewModel() {

    init {
        Log.d("Jai", " FeaturedCollectionsViewModel: init")
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("Jai", " FeaturedCollectionsViewModel: cleared")
    }
}