package nolambda.cachesample.di.modules

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import nolambda.cachesample.network.ApiService
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val DEFAULT_TIMEOUT = 30L
        const val CACHE_CONTROL_HEADER = "Cache-Control"
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://httpbin.org/"

    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {

        val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.DAYS)
                .build()

        val cache = Cache(File(context.cacheDir, "cache"), 10 * 1024 * 1024)

        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .addNetworkInterceptor { chain ->
                    chain.proceed(chain.request())
                            .newBuilder()
                            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
                            .build()
                }
                .addInterceptor { chain ->
                    val request = chain.request()
                            .newBuilder()
                            .cacheControl(cacheControl)
                            .build()
                    chain.proceed(request)
                }
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
