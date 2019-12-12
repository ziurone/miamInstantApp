package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.UserFiltersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFiltersFragmentProvider {

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesUserFiltersFragment(): UserFiltersFragment

}