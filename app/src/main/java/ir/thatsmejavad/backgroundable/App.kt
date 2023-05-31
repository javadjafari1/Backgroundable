package ir.thatsmejavad.backgroundable

import android.app.Application
import ir.thatsmejavad.backgroundable.di.components.AppComponent
import ir.thatsmejavad.backgroundable.di.components.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        private lateinit var appComponent: AppComponent
        val app: AppComponent get() = appComponent

    }
}
