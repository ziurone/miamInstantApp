package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.view.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFiltersFragmentProvider {

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesUserFiltersFragment(): DispensaryFragment

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

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesAddUserDietsFragment(): AddUserDietsFragment

    @ContributesAndroidInjector(modules = [RoomModule::class])
    abstract fun providesAddAlergiesFragment(): AddAllergiesFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesAddAddressFragment(): AddAddressFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class, RoomModule::class])
    abstract fun providesEditUserIngredientFragment(): EditUserIngredientFragment

}