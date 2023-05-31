package ir.thatsmejavad.backgroundable.di.components

import dagger.Component
import ir.thatsmejavad.backgroundable.di.modules.CollectionModule
import ir.thatsmejavad.backgroundable.di.modules.NetworkModule
import ir.thatsmejavad.backgroundable.screens.featuredcollections.FeaturedCollectionsViewModel
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        CollectionModule::class
    ]
)
@Singleton
interface FeaturedCollectionsComponent {

    fun getViewModel(): FeaturedCollectionsViewModel
}
