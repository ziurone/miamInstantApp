package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.actions.SuggestedIngredientWithVolumeUnits
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.entities.SuggestedIngredientEntity
import com.example.miaminstantapp.domain.entities.SuggestedIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import io.reactivex.Completable
import io.reactivex.Single

interface IIngredientRepository {
    fun getSuggestedIngredients(): Single<List<SuggestedIngredientWithVolumeUnits>>
    fun addIngredient(ingredient: Ingredient): Completable
    fun editIngredient(ingredientId: Int, volumeUnitId: Int, volumeUnitQuantity: Int): Completable
    fun getUserIngredients(): Single<List<UserIngredientEntity>>
    fun getIngredientsByName(ingredientName: String): Single<IngredientsListResponse>
    fun getShortIngredientsByName(queryText: String): Single<List<IngredientShortDto>>
    fun getUserIngredientsWithVolumeUnits(): Single<List<UserIngredientWithVolumeUnits>>
    fun getIngredientWithVolumeUnitsById(ingredientId: Int): Single<UserIngredientWithVolumeUnits>
    fun countSuggested(): Single<Int>
    fun fetchSuggestedFromLocal(): Single<List<SuggestedIngredientVolumeUnitRelation>>
    fun fetchFromServer(showedSuggested: Int): Single<IngredientsListResponse>
    fun addSuggestedIngredientsAssociations(ingredients: List<Ingredient>): Completable
    fun removeSuggestedIngredient(ingredient: Ingredient): Completable
}