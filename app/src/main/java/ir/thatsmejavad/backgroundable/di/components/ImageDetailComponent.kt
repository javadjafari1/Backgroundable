package ir.thatsmejavad.backgroundable.di.components

import dagger.Component
import ir.thatsmejavad.backgroundable.di.modules.MediaModule
import ir.thatsmejavad.backgroundable.di.modules.NetworkModule
import ir.thatsmejavad.backgroundable.screens.mediadetail.MediaDetailViewModel
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        MediaModule::class
    ]
)
@Singleton
interface ImageDetailComponent {

    fun getViewModel(): MediaDetailViewModel
}
