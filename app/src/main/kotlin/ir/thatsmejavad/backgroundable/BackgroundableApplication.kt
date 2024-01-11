package ir.thatsmejavad.backgroundable

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import coil.Coil
import coil.ImageLoader
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import ir.thatsmejavad.backgroundable.core.Constants.REQUEST_TIMEOUT_IN_SECONDS
import ir.thatsmejavad.backgroundable.di.components.AppComponent
import ir.thatsmejavad.backgroundable.di.components.DaggerAppComponent
import ir.thatsmejavad.backgroundable.di.modules.ApplicationModule
import ir.thatsmejavad.backgroundable.main.CrashActivity
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class BackgroundableApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        setupCAOC()
        setupCoil()
        setupAppMetrica()
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

    private fun setupAppMetrica() {
        val config = YandexMetricaConfig.newConfigBuilder(BuildConfig.METRICA_TOKEN)
            .withLogs()
            .withAppVersion(BuildConfig.VERSION_NAME)
            .build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)
    }

    private fun setupCAOC() {
        CaocConfig.Builder.create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
            .enabled(!BuildConfig.DEBUG)
            .logErrorOnRestart(false)
            .errorActivity(CrashActivity::class.java)
            .apply()
    }
}
