package com.example.miaminstantapp.domain.actions.recipebook

import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.domain.repositories.IRecipeBookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchRecipeBookRecipeAction @Inject constructor(
    private val recipeBookRepository: IRecipeBookRepository
): BaseAction<FetchRecipeBookRecipeAction.Result>() {

    sealed class Result {
        data class FetchRecipeSuccess(val recipe: RecipeBookRecipeEntity): Result()
    }

    fun fetch(recipeId: Int) {
        recipeBookRepository
            .fetchRecipeById(recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess(recipe: RecipeBookRecipeEntity) {
        liveData.value = Result.FetchRecipeSuccess(recipe)
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}