package ir.thatsmejavad.backgroundable.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.thatsmejavad.backgroundable.core.viewmodel.DaggerViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactoryKey
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelFactory
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailViewModel
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListViewModel

@Module
interface AppViewModelModule {

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(CollectionListViewModel::class)]
    fun bindsCollectionListViewModelFactory(viewModel: CollectionListViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(MediaListViewModel::class)]
    fun bindsMediaListViewModelFactory(factory: MediaListViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(MediaDetailViewModel::class)]
    fun bindsMediaDetailViewModelFactory(factory: MediaDetailViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    fun bindsDaggerViewModelAssistedFactory(factory: DaggerViewModelAssistedFactory): ViewModelFactory
}
