package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.api.MiamApi
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.actions.SuggestedIngredientWithVolumeUnits
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.entities.*
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.example.miaminstantapp.persistence.SuggestedIngredientDao
import com.example.miaminstantapp.persistence.UserIngredientDao
import io.reactivex.Completable
import io.reactivex.Completable.concat
import io.reactivex.Single
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val userIngredientDao: UserIngredientDao,
    private val suggestedIngredientDao: SuggestedIngredientDao,
    private val miamApi: MiamApi
): IIngredientRepository {

    override fun getSuggestedIngredients(): Single<List<SuggestedIngredientWithVolumeUnits>> {
        return suggestedIngredientDao.fetchAll()
    }

    override fun addIngredient(ingredient: Ingredient): Completable {
        val userIngredientVolumeUnitsRelationList = mutableListOf<UserIngredientVolumeUnitRelation>()

        ingredient.volumeUnitsIds.map {
            val userIngredientVolumeUnitRelation = UserIngredientVolumeUnitRelation(ingredient.id, it)
            userIngredientVolumeUnitsRelationList.add(userIngredientVolumeUnitRelation)
        }

        return userIngredientDao
            .addIngredientVolumeUnits(userIngredientVolumeUnitsRelationList)
            .andThen(
                userIngredientDao.add(ingredient.toUserIngredientEntity())
            )
    }

    override fun editIngredient(ingredientId: Int, volumeUnitId: Int, volumeUnitQuantity: Int): Completable = userIngredientDao.update(ingredientId, volumeUnitId, volumeUnitQuantity)

    override fun getUserIngredientsWithVolumeUnits(): Single<List<UserIngredientWithVolumeUnits>> = userIngredientDao.getUserIngredientsWithVolumeUnits()

    override fun getUserIngredients(): Single<List<UserIngredientEntity>> {
        return userIngredientDao.fetchAll()
    }

    override fun getIngredientsByName(ingredientName: String): Single<IngredientsListResponse> {
        return miamApi.searchIngredient(ingredientName)
    }

    override fun getShortIngredientsByName(queryText: String): Single<List<IngredientShortDto>> {
        return Single.just(
            listOf(
                IngredientShortDto(
                    7,
                    "mani"
                ),
                IngredientShortDto(
                    8,
                    "nueces"
                ),
                IngredientShortDto(
                    9,
                    "morron"
                )

            )
        )
    }

    override fun getIngredientWithVolumeUnitsById(ingredientId: Int): Single<UserIngredientWithVolumeUnits> = userIngredientDao.getUserIngredientWithVolumeUnits(ingredientId)

    override fun countSuggested(): Single<Int> = suggestedIngredientDao.countAll()

    override fun fetchSuggestedFromLocal(): Single<List<SuggestedIngredientVolumeUnitRelation>> {
        TODO("Not yet implemented")
    }

    override fun removeSuggestedIngredient(ingredient: Ingredient): Completable {
        return suggestedIngredientDao.removeSuggestedIngredient(ingredient.toSuggestedIngredientEntity())
    }

    override fun addSuggestedIngredientsAssociations(ingredients: List<Ingredient>): Completable {

        return suggestedIngredientDao
            .addAllSuggestedIngredient(ingredients.map { it.toSuggestedIngredientEntity() })
            .andThen ( run {
                val ingredientsWithVolumeUnits = mutableListOf<Pair<Int, Int>>()
                ingredients.map { ingredient ->
                    ingredient.volumeUnitsIds.map {
                        ingredientsWithVolumeUnits.add(Pair(ingredient.id, it))
                    }
                }

                suggestedIngredientDao.addAllSuggestedIngredientsVolumeUnits(
                    ingredientsWithVolumeUnits.map {
                        SuggestedIngredientVolumeUnitRelation(
                            it.first,
                            it.second
                        )
                    })
                }
            )
            .andThen(suggestedIngredientDao.addAllExcludedSuggestedIngredient(ingredients.map { ExcludedSuggestedIngredientEntity(it.id) }))
    }

    override fun fetchFromServer(showedSuggested: Int): Single<IngredientsListResponse> {
        return suggestedIngredientDao
            .fetchAllExcludedSuggestedIngredients()
            .flatMap { excluded -> miamApi
                .fetchSuggestedIngredients(excludedIngredients = excluded.map { it.id }, limit = 5)
            }
    }

}