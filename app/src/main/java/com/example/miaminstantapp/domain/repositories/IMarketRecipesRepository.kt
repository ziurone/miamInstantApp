package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketRecipeDTO
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import com.example.miaminstantapp.domain.relations.DoableRecipe
import io.reactivex.Completable
import io.reactivex.Single

interface IMarketRecipesRepository {
    fun search(searchCriteria: RecipeSearchCriteria): Single<List<MarketRecipeDTO>>
    fun insertAll(recipes: List<MarketRecipeEntity>): Completable
    fun fetchSearchRecipes(): Single<List<DoableRecipe>>
    fun fetchRecipeById(id: Int): Single<DoableRecipe>
    fun deleteAll(): Completable
}