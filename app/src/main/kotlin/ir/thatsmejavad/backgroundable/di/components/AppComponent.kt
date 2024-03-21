package ir.thatsmejavad.backgroundable.di.components

import dagger.Component
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelFactory
import ir.thatsmejavad.backgroundable.di.modules.AppViewModelModule
import ir.thatsmejavad.backgroundable.di.modules.ApplicationModule
import javax.inject.Singleton

@Component(
    modules = [
        AppViewModelModule::class,
        ApplicationModule::class
    ]
)
@Singleton
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory

    fun activityViewModelComponentBuilder(): ActivityViewModelComponent.Builder
}
