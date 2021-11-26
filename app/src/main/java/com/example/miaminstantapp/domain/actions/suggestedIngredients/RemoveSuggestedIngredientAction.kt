package com.example.miaminstantapp.domain.actions.suggestedIngredients

import android.util.Log
import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoveSuggestedIngredientAction @Inject constructor(
    private val ingredientRepository: IIngredientRepository
): BaseAction<RemoveSuggestedIngredientAction.Result>() {

    sealed class Result {
        object Success: Result()
        object Error: Result()
    }

    fun remove(ingredient: Ingredient) {
        ingredientRepository
            .removeSuggestedIngredient(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = Result.Success
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        Log.i("ERROR_REMOVING_ING", throwable.localizedMessage)
        return RemoveSuggestedIngredientAction.Result.Error
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}