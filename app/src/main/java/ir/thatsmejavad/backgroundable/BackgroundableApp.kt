package ir.thatsmejavad.backgroundable

import android.app.Application
import coil.Coil
import coil.ImageLoader
import ir.thatsmejavad.backgroundable.core.Constants.REQUEST_TIMEOUT_IN_SECONDS
import ir.thatsmejavad.backgroundable.di.components.AppComponent
import ir.thatsmejavad.backgroundable.di.components.DaggerAppComponent
import ir.thatsmejavad.backgroundable.di.modules.ApplicationModule
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class BackgroundableApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        setupCoil()
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    private fun setupCoil() {
        val imageLoader = ImageLoader.Builder(applicationContext)
            .okHttpClient {
                val dispatcher = Dispatcher().apply {
                    maxRequestsPerHost = 4
                }
                OkHttpClient.Builder()
                    .readTimeout(REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .connectTimeout(REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .dispatcher(dispatcher)
                    .build()
            }
            .crossfade(true)
            .build()
        Coil.setImageLoader(imageLoader)
    }
}
