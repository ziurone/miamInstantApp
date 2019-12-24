package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchSuggestedIngredientsAction @Inject constructor(
    private val ingredientRepository: IIngredientRepository
) : IFetchSuggestedIngredientsAction, BaseAction<IFetchSuggestedIngredientsAction.Result>()
{

    override fun fetch() {
        ingredientRepository
            .getSuggestedIngredients()
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

    private fun onSuccess(response: SuggestedIngredientsResponse) {
        liveData.value = IFetchSuggestedIngredientsAction.Result.Success(response)
        cleanUp()
    }

}