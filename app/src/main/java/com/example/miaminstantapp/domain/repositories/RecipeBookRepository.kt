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

    override fun fetchRecipesDesc(): Single<List<RecipeBookRecipeEntity>> = recipeBookRecipeDao.fetchRecipesDesc()

    override fun fetchRecipeById(recipeId: Int): Single<RecipeBookRecipeEntity> = recipeBookRecipeDao.fetchRecipeById(recipeId)
}