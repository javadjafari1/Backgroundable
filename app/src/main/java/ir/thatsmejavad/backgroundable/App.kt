package ir.thatsmejavad.backgroundable

import android.app.Application
import ir.thatsmejavad.backgroundable.di.components.AppComponent
import ir.thatsmejavad.backgroundable.di.components.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.create()
    }

    companion object {
        private lateinit var _appComponent: AppComponent
        val appComponent: AppComponent get() = _appComponent
    }
}
