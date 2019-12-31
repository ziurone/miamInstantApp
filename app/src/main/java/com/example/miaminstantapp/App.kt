package com.example.miaminstantapp

import com.example.miaminstantapp.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.google.android.libraries.places.api.Places

open class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        Places.initialize(applicationContext, "AIzaSyCEURjYf0e5I7qJUr7gnTbSVfjSzGvluTw")
        Places.createClient(this)
    }
}