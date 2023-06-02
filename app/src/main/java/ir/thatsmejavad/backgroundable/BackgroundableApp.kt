package ir.thatsmejavad.backgroundable

import android.app.Application
import ir.thatsmejavad.backgroundable.di.components.AppComponent
import ir.thatsmejavad.backgroundable.di.components.DaggerAppComponent

class BackgroundableApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
