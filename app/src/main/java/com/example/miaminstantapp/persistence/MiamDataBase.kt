package com.example.miaminstantapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miaminstantapp.domain.entities.*

@Database(version = 1, exportSchema = false, entities = [
    UserIngredientEntity::class,
    VolumeUnitEntity::class,
    UserIngredientVolumeUnitRelation::class,
    BranchEntity::class,
    ShopEntity::class,
    CatalogRecipeEntity::class,
    MarketIngredientEntityLegacy::class,
    CatalogRecipeUserIngredientEntity::class,
    ShopArticleEntity::class,
    RecipeBookRecipeEntity::class,
    RecipeBookRecipeIngredientEntity::class,
    UserAddressEntity::class,
    DietEntity::class,
    ExcludedIngredientEntity::class,
    SuggestedIngredientVolumeUnitRelation::class,
    SuggestedIngredientEntity::class,
    ExcludedSuggestedIngredientEntity::class])
abstract class MiamDataBase: RoomDatabase() {

    companion object {
        const val NAME = "MIAM_DB"
    }

    abstract fun userIngredientDao(): UserIngredientDao

    abstract fun volumeUnitDao(): VolumeUnitDao

    abstract fun branchDao(): BranchDao

    abstract fun shopDao(): ShopDao

    abstract fun marketIngredientDao(): MarketIngredientDao

    abstract fun marketRecipesDao(): CatalogRecipeDao

    abstract fun userIngredientsOnRecipeDao(): RecipeUserIngredientDao

    abstract fun shopArticlesDao(): ShopArticleDao

    abstract fun userRecipeIngredientsDao(): RecipeBookRecipeIngredientDao

    abstract fun recipeBookRecipeDao(): RecipeBookRecipeDao

    abstract fun userAddressDao(): UserAddressDao

    abstract fun userDietsDao(): DietDao

    abstract fun excludedIngredientsDao(): ExcludedIngredientDao

    abstract fun suggestedIngredientsDao(): SuggestedIngredientDao
}