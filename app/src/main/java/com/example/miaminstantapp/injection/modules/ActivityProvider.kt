package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.MainActivity
import com.example.miaminstantapp.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityProvider {

    @PerActivity
    @ContributesAndroidInjector(modules = [UserFiltersFragmentProvider::class, UserResourcesModule::class])
    abstract fun bindUserResourcesActivity(): MainActivity

}