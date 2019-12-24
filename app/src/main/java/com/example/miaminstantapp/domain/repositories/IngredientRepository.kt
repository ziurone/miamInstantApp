package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.toUserIngredientEntity
import com.example.miaminstantapp.persistence.UserIngredientDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val userIngredientDao: UserIngredientDao
): IIngredientRepository {

    override fun getSuggestedIngredients(): Single<SuggestedIngredientsResponse> {
        return Single.just(
            SuggestedIngredientsResponse(listOf<Ingredient>(
                Ingredient(1, "sal", 1, 100, listOf(1,2,3)),
                Ingredient(2, "aceite", 2, 100, listOf(1,2,3)),
                Ingredient(3, "zanahoria", 2, 100, listOf(1,2,3))
            ))
        )

    }

    override fun addIngredient(ingredient: Ingredient): Completable {
        val userIngredientVolumeUnitsRelationList = mutableListOf<UserIngredientVolumeUnitRelation>()

        ingredient.volumeUnitIds.map {
            val userIngredientVolumeUnitRelation = UserIngredientVolumeUnitRelation(ingredient.id, it)
            userIngredientVolumeUnitsRelationList.add(userIngredientVolumeUnitRelation)
        }

        return userIngredientDao
            .addIngredientVolumeUnits(userIngredientVolumeUnitsRelationList)
            .andThen(
                userIngredientDao.add(ingredient.toUserIngredientEntity())
            )
    }

    override fun getUserIngredients(): Single<List<UserIngredientEntity>> {
        return userIngredientDao.fetchAll()
    }

}