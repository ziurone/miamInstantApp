package com.example.miaminstantapp.domain.actions.recipebook

import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import com.example.miaminstantapp.domain.repositories.IRecipeBookRecipeIngredientRepository
import com.example.miaminstantapp.domain.repositories.IRecipeBookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchRecipeBookIngredientsAction @Inject constructor(
    private val recipeBookRecipeIngredientsRepository: IRecipeBookRecipeIngredientRepository
): BaseAction<FetchRecipeBookIngredientsAction.Result>() {

    sealed class Result {
        data class FetchIngredientsSuccess(val ingredients: List<RecipeBookRecipeIngredientEntity>): Result()
    }

    fun fetch(recipeId: Int) {
        recipeBookRecipeIngredientsRepository
            .fetchIngredients(recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess(ingredients: List<RecipeBookRecipeIngredientEntity>) {
        liveData.value = Result.FetchIngredientsSuccess(ingredients)
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}