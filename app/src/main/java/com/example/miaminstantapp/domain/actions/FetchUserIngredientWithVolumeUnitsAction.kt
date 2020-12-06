package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchUserIngredientWithVolumeUnitsAction @Inject constructor(
    private val ingredientRepository: IIngredientRepository
): BaseAction<FetchUserIngredientWithVolumeUnitsAction.Result>() {
    sealed class Result {
        data class Success(val ingredient: UserIngredientWithVolumeUnits): Result()
        object Error: Result()
    }

    fun fetch(ingredientId: Int) {
        ingredientRepository
            .getIngredientWithVolumeUnitsById(ingredientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onFetchIngredientSuccess, ::onError)
            .track()
    }

    private fun onFetchIngredientSuccess(ingredient: UserIngredientWithVolumeUnits) {
        liveData.value = Result.Success(ingredient)
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }

}