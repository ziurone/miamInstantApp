package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.repositories.IUserRecipeIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddRecipeUserIngredientAction @Inject constructor(
    private val userRecipeIngredientRepository: IUserRecipeIngredientRepository
): BaseAction<AddRecipeUserIngredientAction.Result>() {

    sealed class Result {
        object Success: Result()
    }

    fun addIngredient(ingredient: CatalogRecipeUserIngredientEntity) {
        userRecipeIngredientRepository
            .add(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onAddIngredientSuccess, ::onError)
            .track()

    }

    private fun onAddIngredientSuccess() {
        liveData.value = Result.Success
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }


}