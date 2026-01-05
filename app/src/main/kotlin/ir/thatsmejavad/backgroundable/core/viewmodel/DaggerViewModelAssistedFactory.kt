package ir.thatsmejavad.backgroundable.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

@Suppress(
    "ktlint:standard:parameter-list-spacing",
    "ktlint:standard:indent",
)
class DaggerViewModelAssistedFactory @Inject constructor(
    private val assistedFactoryMap:
    Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModelAssistedFactory<*>>>,
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

        val create = when {
            creator != null -> creator.get().create(handle)
            viewModels[modelClass] != null -> viewModels.getValue(modelClass).get()
            else -> throw IllegalArgumentException("Unknown model class $modelClass")
        }
        return create as VM
    }
}
