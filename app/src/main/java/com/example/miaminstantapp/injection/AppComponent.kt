package com.example.miaminstantapp.injection

import com.example.miaminstantapp.App
import com.example.miaminstantapp.injection.modules.*
import com.example.miaminstantapp.scopes.PerApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityProvider::class,
    RoomModule::class,
    NetworkModule::class
])

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}