package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.CatalogRecipeDto
import com.example.miaminstantapp.domain.dtos.MarketRecipeDTOLegacy
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntityLegacy
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import io.reactivex.Completable
import io.reactivex.Single

interface ICatalogRecipesRepository {
    fun searchLegacy(searchCriteria: RecipeSearchCriteria): Single<List<MarketRecipeDTOLegacy>>
    fun search(searchCriteria: RecipeSearchCriteria): Single<List<CatalogRecipeDto>>
    fun insertAllLegacy(recipeLegacies: List<CatalogRecipeEntityLegacy>): Completable
    fun insertAll(recipeLegacies: List<CatalogRecipeDto>): Completable
    fun fetchSearchRecipes(): Single<List<CatalogRecipeRelations>>
    fun fetchRecipeById(id: Int): Single<CatalogRecipeRelations>
    fun deleteAll(): Completable
}