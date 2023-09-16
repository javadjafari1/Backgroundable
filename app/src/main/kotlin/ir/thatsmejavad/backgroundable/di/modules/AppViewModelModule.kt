package ir.thatsmejavad.backgroundable.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactoryKey
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import ir.thatsmejavad.backgroundable.screens.downloadpicker.DownloadPickerViewModel
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailViewModel
import ir.thatsmejavad.backgroundable.screens.medialist.MediaListViewModel
import ir.thatsmejavad.backgroundable.screens.search.SearchViewModel
import ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting.ImageQualitySettingViewModel
import ir.thatsmejavad.backgroundable.screens.settings.themesetting.ThemeSettingViewModel

@Module(
    includes = [
        ViewModelFactoryModule::class,
        NetworkModule::class,
        CollectionModule::class,
        MediaModule::class,
        SettingsModule::class,
    ]
)
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
    @[IntoMap ViewModelAssistedFactoryKey(SearchViewModel::class)]
    fun bindsSearchViewModelFactory(viewModel: SearchViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(ThemeSettingViewModel::class)]
    fun bindsThemeSettingViewModelFactory(viewModel: ThemeSettingViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(DownloadPickerViewModel::class)]
    fun bindsDownloadPickerViewModelFactory(factory: DownloadPickerViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(ImageQualitySettingViewModel::class)]
    fun bindsImageQualitySettingViewModelFactory(viewModel: ImageQualitySettingViewModel): ViewModel
}
