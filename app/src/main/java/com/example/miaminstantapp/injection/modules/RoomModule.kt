package com.example.miaminstantapp.injection.modules

import android.content.Context
import androidx.room.Room
import com.example.miaminstantapp.injection.qualifiers.AppContext
import com.example.miaminstantapp.persistence.*
import com.example.miaminstantapp.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provideRoomDB(@AppContext context: Context): MiamDataBase = Room.databaseBuilder(context, MiamDataBase::class.java, MiamDataBase.NAME).build()

    @Provides
    @PerApplication
    fun provideUserIngredientDao(miamDataBase: MiamDataBase): UserIngredientDao = miamDataBase.userIngredientDao()

    @Provides
    @PerApplication
    fun provideVolumeUnitDao(miamDataBase: MiamDataBase): VolumeUnitDao = miamDataBase.volumeUnitDao()

    @Provides
    @PerApplication
    fun provideBranchesDao(miamDataBase: MiamDataBase): BranchDao = miamDataBase.branchDao()

    @Provides
    @PerApplication
    fun provideDietsDao(miamDataBase: MiamDataBase): DietDao = miamDataBase.userDietsDao()

    @Provides
    @PerApplication
    fun providesShopDao(miamDataBase: MiamDataBase): ShopDao = miamDataBase.shopDao()

    @Provides
    fun providesCatalogRecipeDao(miamDataBase: MiamDataBase): CatalogRecipeDao = miamDataBase.marketRecipesDao()

    @Provides
    fun providesMarketIngredientsDao(miamDataBase: MiamDataBase): MarketIngredientDao = miamDataBase.marketIngredientDao()

    @Provides
    fun providesRecipeUserIngredientsDao(miamDataBase: MiamDataBase): CatalogRecipeUserIngredientDao = miamDataBase.userIngredientsOnRecipeDao()

    @Provides
    @PerApplication
    fun providesShopArticlesDao(miamDataBase: MiamDataBase): ShopArticleDao = miamDataBase.shopArticlesDao()

    @Provides
    @PerApplication
    fun providesuserRecipeIngredientsDao(miamDataBase: MiamDataBase): RecipeBookRecipeIngredientDao = miamDataBase.userRecipeIngredientsDao()

    @Provides
    @PerApplication
    fun providesRecipeBookRecipeDao(miamDataBase: MiamDataBase): RecipeBookRecipeDao = miamDataBase.recipeBookRecipeDao()

    @Provides
    @PerApplication
    fun providesUserAddressDao(miamDataBase: MiamDataBase): UserAddressDao = miamDataBase.userAddressDao()

    @Provides
    @PerApplication
    fun providesExcludedIngredientsDao(miamDataBase: MiamDataBase): ExcludedIngredientDao = miamDataBase.excludedIngredientsDao()

    @Provides
    @PerApplication
    fun providesSuggestedIngredientsDato(miamDataBase: MiamDataBase): SuggestedIngredientDao = miamDataBase.suggestedIngredientsDao()
}