package ir.thatsmejavad.backgroundable.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

class DaggerViewModelAssistedFactory @Inject constructor(
    private val assistedFactoryMap:
    Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModelAssistedFactory<*>>>,
    private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(modelClass: Class<VM>, handle: SavedStateHandle): VM {
        return assistedFactoryMap[modelClass]?.get()?.create(handle) as? VM
            ?: viewModelMap[modelClass]?.get() as? VM
            ?: throw IllegalArgumentException("unknown model class $modelClass")
    }
}
