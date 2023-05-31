package ir.thatsmejavad.backgroundable.di.components

import dagger.Component
import ir.thatsmejavad.backgroundable.di.modules.CollectionModule
import ir.thatsmejavad.backgroundable.di.modules.NetworkModule
import ir.thatsmejavad.backgroundable.screens.collectionlist.CollectionListViewModel
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        CollectionModule::class
    ]
)
@Singleton
interface CollectionListComponent {

    fun getViewModel(): CollectionListViewModel
}
