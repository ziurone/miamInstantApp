package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketRecipeDTO
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity
import com.example.miaminstantapp.domain.relations.CatalogRecipe
import io.reactivex.Completable
import io.reactivex.Single

interface IMarketRecipesRepository {
    fun search(searchCriteria: RecipeSearchCriteria): Single<List<MarketRecipeDTO>>
    fun insertAll(recipes: List<CatalogRecipeEntity>): Completable
    fun fetchSearchRecipes(): Single<List<CatalogRecipe>>
    fun fetchRecipeById(id: Int): Single<CatalogRecipe>
    fun deleteAll(): Completable
}