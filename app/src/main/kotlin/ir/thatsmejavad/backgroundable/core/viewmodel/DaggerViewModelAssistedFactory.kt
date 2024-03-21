package ir.thatsmejavad.backgroundable.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

class DaggerViewModelAssistedFactory @Inject constructor(
    private val assistedFactoryMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModelAssistedFactory<*>>>,
    private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelFactory {
    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(
        modelClass: Class<VM>,
        handle: SavedStateHandle
    ): VM {
        val creator =
            assistedFactoryMap[modelClass]
                ?: assistedFactoryMap.asIterable()
                    .firstOrNull { modelClass.isInstance(it.key) }?.value

        val create = creator?.get()?.create(handle)
            ?: viewModels[modelClass]?.get()
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        return create as VM
    }
}
