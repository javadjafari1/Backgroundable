package ir.thatsmejavad.backgroundable.di.components

import dagger.Component
import ir.thatsmejavad.backgroundable.di.modules.BindModule
import ir.thatsmejavad.backgroundable.di.modules.NetworkModule
import ir.thatsmejavad.backgroundable.screens.featuredcollections.FeaturedCollectionsViewModel
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        BindModule::class
    ]
)
@Singleton
interface FeaturedCollectionsComponent {

    @Component.Builder
    interface Builder {
        fun build(): FeaturedCollectionsComponent
    }

    fun getViewModel(): FeaturedCollectionsViewModel
}
