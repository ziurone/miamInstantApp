package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.view.UserFiltersFragment
import com.example.miaminstantapp.view.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFiltersFragmentProvider {

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesUserFiltersFragment(): UserFiltersFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesDoableRecipesListFragment(): DoableRecipesListFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesDoableRecipeFragment(): DoableRecipeFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesTicketFragment(): ShopPurchaseTicketFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesTicketArticlesFragment(): TicketArticlesFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesRecipeBookFragment(): RecipeBookFragment

}