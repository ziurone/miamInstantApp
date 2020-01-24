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
    MarketRecipeEntity::class,
    MarketIngredientEntity::class,
    DoableRecipeUserIngredient::class,
    ShopArticleEntity::class,
    RecipeBookRecipeEntity::class,
    RecipeBookRecipeIngredientEntity::class])
abstract class MiamDataBase: RoomDatabase() {

    companion object {
        const val NAME = "MIAM_DB"
    }

    abstract fun userIngredientDao(): UserIngredientDao

    abstract fun volumeUnitDao(): VolumeUnitDao

    abstract fun branchDao(): BranchDao

    abstract fun shopDao(): ShopDao

    abstract fun marketIngredientDao(): MarketIngredientDao

    abstract fun marketRecipesDao(): MarketRecipeDao

    abstract fun userIngredientsOnRecipeDao(): RecipeUserIngredientDao

    abstract fun shopArticlesDao(): ShopArticleDao

    abstract fun userRecipeIngredientsDao(): RecipeBookRecipeIngredientDao

    abstract fun recipeBookRecipeDao(): RecipeBookRecipeDao
}