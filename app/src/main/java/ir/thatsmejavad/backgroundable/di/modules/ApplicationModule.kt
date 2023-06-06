package ir.thatsmejavad.backgroundable.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
object ApplicationModule {
    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}
