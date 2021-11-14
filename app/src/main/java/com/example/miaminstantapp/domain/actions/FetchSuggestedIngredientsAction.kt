package com.example.miaminstantapp.domain.actions

import android.util.Log
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

    override fun fetch(excludeIngredientsIds: List<Int>) {
        ingredientRepository
            .countSuggested()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { c -> onCountSuggestedSuccess(c, excludeIngredientsIds) }, ::onError)
            .track()
    }

    private fun onCountSuggestedSuccess(suggestedCount: Int, excludeIngredientsIds: List<Int>) {
        if(suggestedCount < 5) {
            ingredientRepository
                .refreshSuggested(excludeIngredientsIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ fetchSuggested(excludeIngredientsIds) }, ::onError)
                .track()
        } else {
            fetchSuggested(excludeIngredientsIds)
        }
    }

    private fun fetchSuggested(excludeIngredientsIds: List<Int>) {
        ingredientRepository
            .getSuggestedIngredients(excludeIngredientsIds)
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