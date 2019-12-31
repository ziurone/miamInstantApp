package com.example.miaminstantapp.injection.modules

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.miaminstantapp.App
import com.example.miaminstantapp.injection.qualifiers.AppContext
import com.example.miaminstantapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @AppContext
    abstract fun provideContext(app: App): Context

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


}