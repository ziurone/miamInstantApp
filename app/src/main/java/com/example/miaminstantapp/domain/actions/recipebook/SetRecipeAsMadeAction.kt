package com.example.miaminstantapp.domain.actions.recipebook

import android.util.Log
import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.domain.repositories.IRecipeBookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SetRecipeAsMadeAction @Inject constructor(
    private val recipeBookRepository: IRecipeBookRepository
): BaseAction<SetRecipeAsMadeAction.Result>() {

    sealed class Result {
        object RecipeSetAsMadeSuccess: Result()
        object RecipeSetAsMadeError: Result()
    }

    fun set(recipeId: Int) {
        recipeBookRepository
            .setRecipeAsMade(recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = Result.RecipeSetAsMadeSuccess
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        Log.i("ERROOR", throwable.localizedMessage)
        return Result.RecipeSetAsMadeError
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}