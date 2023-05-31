package ir.thatsmejavad.backgroundable.di.components

import dagger.Component
import ir.thatsmejavad.backgroundable.di.modules.BindModule
import ir.thatsmejavad.backgroundable.di.modules.NetworkModule
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailViewModel
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        BindModule::class
    ]
)
@Singleton
interface ImageDetailComponent {

    @Component.Builder
    interface Builder {
        fun build(): ImageDetailComponent
    }

    fun getViewModel(): MediaDetailViewModel
}
