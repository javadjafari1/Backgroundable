package ir.thatsmejavad.backgroundable.di.components

import androidx.activity.ComponentActivity
import dagger.BindsInstance
import dagger.Subcomponent
import ir.thatsmejavad.backgroundable.di.modules.MainViewModelModule
import ir.thatsmejavad.backgroundable.main.MainActivity

@Subcomponent(modules = [MainViewModelModule::class])
interface ActivityViewModelComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun componentActivity(activity: ComponentActivity): Builder
        fun build(): ActivityViewModelComponent
    }

    fun inject(activity: MainActivity)
}
