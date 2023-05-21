package ir.thatsmejavad.backgroundable.data

import ir.thatsmejavad.backgroundable.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", BuildConfig.AUTHORIZATION)
            .build()

        return chain.proceed(request)
    }
}
