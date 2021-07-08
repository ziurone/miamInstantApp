package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.SessionActivity
import com.example.miaminstantapp.scopes.PerActivity
import com.example.miaminstantapp.view.PresentationFlowActivity
import com.example.miaminstantapp.view.SetUserFiltersActivity
import com.example.miaminstantapp.view.SplashScreenActivity
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityProvider {

    @PerActivity
    @ContributesAndroidInjector(modules = [UserFiltersFragmentProvider::class, UserResourcesModule::class])
    abstract fun bindUserResourcesActivity(): SessionActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserFiltersFragmentProvider::class, UserResourcesModule::class])
    abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserFiltersFragmentProvider::class, UserResourcesModule::class])
    abstract fun bindPresentationFlowActivity(): PresentationFlowActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserFiltersFragmentProvider::class, UserResourcesModule::class])
    abstract fun bindUserFiltersActivity(): SetUserFiltersActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserFiltersFragmentProvider::class, UserResourcesModule::class])
    abstract fun bindCatalogRecipeDetailActivity(): CatalogRecipeDetailActivity
}