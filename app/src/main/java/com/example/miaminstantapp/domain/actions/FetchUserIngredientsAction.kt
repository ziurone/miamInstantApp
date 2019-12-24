package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.repositories.IngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchUserIngredientsAction @Inject constructor(
    private val ingredientRepository: IngredientRepository
):
    BaseAction<IFetchUserIngredientsAction.Result>(),
    IFetchUserIngredientsAction {

    override fun fetch() {
        ingredientRepository
            .getUserIngredients()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchUserIngredientsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchUserIngredientsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess(ingredients: List<UserIngredientEntity>) {
        liveData.value = IFetchUserIngredientsAction.Result.Success(ingredients)
    }

}