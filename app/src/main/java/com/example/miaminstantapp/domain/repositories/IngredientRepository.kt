package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse
import io.reactivex.Single
import javax.inject.Inject

class IngredientRepository @Inject constructor(

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
}