package ir.thatsmejavad.backgroundable.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import ir.thatsmejavad.backgroundable.data.AuthorizationInterceptor
import ir.thatsmejavad.backgroundable.data.PexelsApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            // add naming strategy
        }
    }

    @Singleton
    @Provides
    fun provideKotlinSerializationFactory(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authorizationInterceptor: AuthorizationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(loggingInterceptor)
            .callTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        kotlinConverter: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(kotlinConverter)
            .baseUrl("https://api.pexels.com/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providePexelsApi(retrofit: Retrofit): PexelsApi {
        return retrofit.create(PexelsApi::class.java)
    }
}
