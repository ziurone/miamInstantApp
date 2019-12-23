package com.example.miaminstantapp.domain.usecases

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import com.example.miaminstantapp.domain.repositories.IngredientRepository
import com.example.miaminstantapp.persistence.UserIngredientDao
import io.reactivex.Completable
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IAddUserIngredientAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleResponse() {
        liveData.value = IAddUserIngredientAction.Result.Success
    }
}