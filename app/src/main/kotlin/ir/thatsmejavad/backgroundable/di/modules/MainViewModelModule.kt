package ir.thatsmejavad.backgroundable.di.modules

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ir.thatsmejavad.backgroundable.core.viewmodel.MainViewModelFactory
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.main.MainViewModel

@Module
class MainViewModelModule {
    @Provides
    fun provideMyViewModel(
        activity: ComponentActivity,
        settingRepository: SettingRepository,
    ): MainViewModel {
        return ViewModelProvider(
            store = activity.viewModelStore,
            factory = MainViewModelFactory(repository = settingRepository)
        )[MainViewModel::class.java]
    }
}
