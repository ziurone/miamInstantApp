package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchSuggestedIngredientsAction @Inject constructor(
    private val ingredientRepository: IIngredientRepository
) : IFetchSuggestedIngredientsAction, BaseAction<IFetchSuggestedIngredientsAction.Result>()
{

    override fun fetch(alreadyAddedIngredients: List<UserIngredientEntity>) {
        ingredientRepository
            .getSuggestedIngredients(alreadyAddedIngredients)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchSuggestedIngredientsAction.Result? {
        return IFetchSuggestedIngredientsAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IFetchSuggestedIngredientsAction.Result? {
        return IFetchSuggestedIngredientsAction.Result.Error(failedResponseCode)
    }

    private fun onSuccess(listResponse: IngredientsListResponse) {
        liveData.value = IFetchSuggestedIngredientsAction.Result.Success(listResponse)
        cleanUp()
    }

}