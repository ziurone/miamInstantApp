package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.domain.repositories.IExcludedIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddExcludedIngredientAction @Inject constructor(
    private val excludedIngredientRepository: IExcludedIngredientRepository
): BaseAction<AddExcludedIngredientAction.Result>() {

    sealed class Result {
        data class Success(val excludedIngredient: IngredientShortDto): Result()
    }

    fun add(excludedIngredient: IngredientShortDto) {
        excludedIngredientRepository
            .add(excludedIngredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({onSuccess(excludedIngredient)}, ::onError)
            .track()
    }

    fun onSuccess(excludedIngredient: IngredientShortDto) {
        liveData.value = Result.Success(excludedIngredient)
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }


}