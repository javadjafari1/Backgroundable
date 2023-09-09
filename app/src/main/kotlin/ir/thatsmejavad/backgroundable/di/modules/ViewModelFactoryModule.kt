package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import ir.thatsmejavad.backgroundable.core.viewmodel.DaggerViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelFactory

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsDaggerViewModelAssistedFactory(factory: DaggerViewModelAssistedFactory): ViewModelFactory
}
