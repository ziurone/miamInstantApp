package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddUserIngredientAction @Inject constructor(
    private val ingredientRepository: IIngredientRepository
) : IAddUserIngredientAction, BaseAction<IAddUserIngredientAction.Result>() {

    override fun add(ingredient: Ingredient) {
        ingredientRepository
            .addIngredient(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleResponse, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IAddUserIngredientAction.Result? {
        return IAddUserIngredientAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IAddUserIngredientAction.Result? {
        return IAddUserIngredientAction.Result.Error(failedResponseCode)
    }

    private fun handleResponse() {
        liveData.value = IAddUserIngredientAction.Result.Success
    }
}