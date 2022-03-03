package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.api.MiamApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(
        @HttpClient client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.dev.miam.cloud/app_dev.php/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides fun provideStandardOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides @HttpClient
    fun providesHttpClient(
        client: OkHttpClient
    ): OkHttpClient {
        val clientBuilder = client.newBuilder().addInterceptor(HttpLoggingInterceptor().apply { level= HttpLoggingInterceptor.Level.BODY })
        return clientBuilder.build()
    }


    @Provides
    fun providesVolumeUnitApi(retrofit: Retrofit): MiamApi = retrofit.create(MiamApi::class.java)

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME) annotation class HttpClient

}