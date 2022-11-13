package com.yangbong.damedame.di

import com.yangbong.damedame.BuildConfig.*
import com.yangbong.damedame.di.annotations.DameDameServer
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.CustomCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    @DameDameServer
    fun providesDameDameInterceptor(localPreferenceUserDataSourceImpl: LocalPreferenceUserDataSource): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            localPreferenceUserDataSourceImpl.getAccessToken()
                        )
                        .build()
                )
            }
        }


    @Provides
    @Singleton
    @DameDameServer
    fun providesDameDameOkHttpClient(
        @DameDameServer interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @DameDameServer
    fun providesDameDameRetrofit(@DameDameServer okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(if (DEBUG) DAME_DAME_SERVER_BASE_URL_DEBUG else DAME_DAME_SERVER_BASE_URL_RELEASE)
            .client(okHttpClient)
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
