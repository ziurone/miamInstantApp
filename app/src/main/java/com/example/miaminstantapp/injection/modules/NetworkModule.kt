package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.api.MiamApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://10.0.2.2:81/app_dev.php/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesVolumeUnitApi(retrofit: Retrofit): MiamApi = retrofit.create(MiamApi::class.java)

}