package ir.thatsmejavad.backgroundable.di.modules

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactoryKey
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailViewModel
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListViewModel

@Module(
    includes = [
        ViewModelFactoryModule::class,
        NetworkModule::class,
        CollectionModule::class,
        MediaModule::class,
    ]
)
interface AppViewModelModule {

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(CollectionListViewModel::class)]
    fun bindsCollectionListViewModelFactory(factory: CollectionListViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(MediaListViewModel::class)]
    fun bindsMediaListViewModelFactory(factory: MediaListViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(MediaDetailViewModel::class)]
    fun bindsMediaDetailViewModelFactory(factory: MediaDetailViewModel.Factory): ViewModelAssistedFactory<*>
}
