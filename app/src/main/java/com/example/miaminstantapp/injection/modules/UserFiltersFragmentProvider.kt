package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.UserFiltersFragment
import com.example.miaminstantapp.view.DoableRecipeFragment
import com.example.miaminstantapp.view.DoableRecipesListFragment
import com.example.miaminstantapp.view.TicketArticlesFragment
import com.example.miaminstantapp.view.TicketFragment
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
    abstract fun providesTicketFragment(): TicketFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesTicketArticlesFragment(): TicketArticlesFragment

}