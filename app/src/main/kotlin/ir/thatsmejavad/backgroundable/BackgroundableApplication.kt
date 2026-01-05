package ir.thatsmejavad.backgroundable

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
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
        SingletonImageLoader.setSafe {
            ImageLoader.Builder(applicationContext)
                .components {
                    add(
                        OkHttpNetworkFetcherFactory(
                            callFactory = {
                                val dispatcher = Dispatcher().apply {
                                    maxRequestsPerHost = 4
                                }
                                OkHttpClient.Builder()
                                    .readTimeout(
                                        timeout = REQUEST_TIMEOUT_IN_SECONDS,
                                        unit = TimeUnit.SECONDS
                                    )
                                    .connectTimeout(
                                        timeout = REQUEST_TIMEOUT_IN_SECONDS,
                                        unit = TimeUnit.SECONDS
                                    )
                                    .dispatcher(dispatcher)
                                    .build()
                            }
                        )
                    )
                }
                .crossfade(true)
                .build()
        }
    }

    private fun setupAppMetrica() {
        val config = AppMetricaConfig.newConfigBuilder(BuildConfig.METRICA_TOKEN)
            .withLogs()
            .withAppVersion(BuildConfig.VERSION_NAME)
            .build()
        AppMetrica.activate(applicationContext, config)
        AppMetrica.enableActivityAutoTracking(this)
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
