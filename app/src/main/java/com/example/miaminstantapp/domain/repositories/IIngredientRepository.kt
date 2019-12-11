package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse
import io.reactivex.Single

interface IIngredientRepository {
    fun getSuggestedIngredients(): Single<SuggestedIngredientsResponse>
}