package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.repositories.IngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditUserIngredientAction @Inject constructor(
    private val ingredientRepository: IngredientRepository
): BaseAction<EditUserIngredientAction.Result>()  {

    sealed class Result {
        object EditedSuccess: Result()
        object Error: Result()
    }

    fun edit(ingredientId: Int, volumeUnitId: Int, volumeUnitQuantity: Int) {
        ingredientRepository
            .editIngredient(ingredientId, volumeUnitId, volumeUnitQuantity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onEditUserIngredientSuccess, ::onError)
            .track()
    }

    private fun onEditUserIngredientSuccess() {
        liveData.value = Result.EditedSuccess
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}