package com.example.miaminstantapp.injection.modules

import com.example.miaminstantapp.view.*
import com.example.miaminstantapp.view.ingredients.IngredientAutocompleteFragment
import com.example.miaminstantapp.view.recipe_book.RecipeBookToDoRecipesFragment
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailFragment
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailIngredientsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFiltersFragmentProvider {

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesUserFiltersFragment(): DispensaryFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesDoableRecipesListFragment(): CatalogRecipesListFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesDoableRecipeFragment(): CatalogRecipeDetailFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesTicketFragment(): ShoppingCartListFragment

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

    @ContributesAndroidInjector(modules = [UserResourcesModule::class, RoomModule::class])
    abstract fun providesCatalogRecipeDetailIngredientsListFragment(): CatalogRecipeDetailIngredientsListFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesIngredientAutocompleteFragment(): IngredientAutocompleteFragment

    @ContributesAndroidInjector(modules = [UserResourcesModule::class])
    abstract fun providesRecipeBookToDoRecipesFragment(): RecipeBookToDoRecipesFragment

}