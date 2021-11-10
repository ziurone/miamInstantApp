package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.api.MiamApi
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.toUserIngredientEntity
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.example.miaminstantapp.persistence.UserIngredientDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val userIngredientDao: UserIngredientDao,
    private val miamApi: MiamApi
): IIngredientRepository {

    override fun getSuggestedIngredients(excludeIngredientsIds: List<Int>): Single<IngredientsListResponse> {

        return miamApi.fetchSuggestedIngredients(excludedIngredients = excludeIngredientsIds)

//        val suggestedIngredients = mutableListOf(
//            Ingredient(1, "sal", 1, 100, listOf(1,2,3)),
//            Ingredient(2, "aceite", 2, 100, listOf(1,2,3)),
//            Ingredient(3, "zanahoria", 2, 100, listOf(1,2,3))
//        )
//
//        if(excludeIngredientsIds.isNotEmpty() && excludeIngredientsIds.size.equals(suggestedIngredients.size)) {
//            suggestedIngredients.add(Ingredient(7, "huevo", 2, 100, listOf(1,2)))
//        }
//
//        return Single.just(
//            IngredientsListResponse(suggestedIngredients.filter { ingredient -> !excludeIngredientsIds.contains(ingredient.id)})
//        )

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
        return Single.just(
            IngredientsListResponse(
                listOf(
                    Ingredient(10, "Batata", 1, 100, listOf(1,2,3)),
                    Ingredient(11, "Papa", 1, 100, listOf(1,2,3)),
                    Ingredient(12, "Crema", 1, 100, listOf(1,2,3))
                )
            )
        )
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

}