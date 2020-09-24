package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchShortIngredientsByNameAction @Inject constructor(
    private val ingredientRepository: IIngredientRepository
): BaseAction<FetchShortIngredientsByNameAction.Result>() {

    sealed class Result {
        data class Success(val ingredients: List<IngredientShortDto>): Result()
    }

    fun search(text: String) {
        ingredientRepository
            .getShortIngredientsByName(text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSearchSuccess, ::onError)
            .track()
    }

    private fun onSearchSuccess(ingredients: List<IngredientShortDto>) {
        liveData.value = Result.Success(ingredients)
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }

}