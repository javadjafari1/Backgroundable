package ir.thatsmejavad.backgroundable.di.components

import dagger.Component
import ir.thatsmejavad.backgroundable.di.modules.BindModule
import ir.thatsmejavad.backgroundable.di.modules.NetworkModule
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        BindModule::class
    ]
)
@Singleton
interface CollectionListComponent {

    @Component.Builder
    interface Builder {
        fun build(): CollectionListComponent
    }

    fun getViewModel(): CollectionListViewModel
}
