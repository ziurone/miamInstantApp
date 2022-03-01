package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.persistence.RecipeBookRecipeDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecipeBookRepository @Inject constructor(
    private val recipeBookRecipeDao: RecipeBookRecipeDao
): IRecipeBookRepository {
    override fun addRecipe(recipe: RecipeBookRecipeEntity): Completable = recipeBookRecipeDao.addRecipeToRecipeBook(recipe)

    override fun fetchRecipesDesc(made: Boolean): Single<List<RecipeBookRecipeEntity>> = recipeBookRecipeDao.fetchRecipesDesc(made)

    override fun fetchRecipeById(recipeId: Int): Single<RecipeBookRecipeEntity> = recipeBookRecipeDao.fetchRecipeById(recipeId)

    override fun setRecipeAsMade(recipeId: Int): Completable = recipeBookRecipeDao.setRecipeAsMade(recipeId)

    override fun fetchRecipeBookRecipesIds(): Single<List<Int>> = recipeBookRecipeDao.fetchRecipesIds()
}