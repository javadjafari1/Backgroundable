package ir.thatsmejavad.backgroundable.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

interface ViewModelFactory {
    fun <VM : ViewModel> create(modelClass: Class<VM>, handle: SavedStateHandle): VM
}

class DaggerViewModelAssistedFactory @Inject constructor(
    private val assistedFactoryMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModelAssistedFactory<*>>>
) : ViewModelFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(modelClass: Class<VM>, handle: SavedStateHandle): VM {
        val creator =
            assistedFactoryMap[modelClass] ?: assistedFactoryMap.asIterable().firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get().create(handle) as VM
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
