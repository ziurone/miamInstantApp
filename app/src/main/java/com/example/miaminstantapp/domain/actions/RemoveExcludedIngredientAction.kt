package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.repositories.IExcludedIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoveExcludedIngredientAction @Inject constructor(
    private val excludedIngredientRepository: IExcludedIngredientRepository
): BaseAction<RemoveExcludedIngredientAction.Result>() {
    sealed class Result {
        object Success: Result()
    }

    fun remove(ingredientId: Int) {
        excludedIngredientRepository
            .remove(ingredientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    fun onSuccess() {
        liveData.value = Result.Success
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}