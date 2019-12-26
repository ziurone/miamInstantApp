package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import com.example.miaminstantapp.domain.repositories.IngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetIngredientsByNameAction @Inject constructor(
    private val ingredientRepository: IIngredientRepository
):
    BaseAction<IGetIngredientsByNameAction.Result>(),
    IGetIngredientsByNameAction {

    override fun getErrorResult(throwable: Throwable): IGetIngredientsByNameAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IGetIngredientsByNameAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getIngredients(ingredientName: String) {
        ingredientRepository
            .getIngredientsByName(ingredientName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess(ingredientsResponse: IngredientsListResponse) {
        liveData.value = IGetIngredientsByNameAction.Result.Success(ingredientsResponse.ingredients)
    }
}