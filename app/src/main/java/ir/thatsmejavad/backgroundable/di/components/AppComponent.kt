package ir.thatsmejavad.backgroundable.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelFactory
import ir.thatsmejavad.backgroundable.di.modules.AppViewModelModule
import ir.thatsmejavad.backgroundable.di.modules.ApplicationModule
import ir.thatsmejavad.backgroundable.di.modules.CollectionModule
import ir.thatsmejavad.backgroundable.di.modules.MediaModule
import ir.thatsmejavad.backgroundable.di.modules.NetworkModule
import javax.inject.Singleton

@Component(
    modules = [
        AppViewModelModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        CollectionModule::class,
        MediaModule::class,
    ]
)
@Singleton
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}
