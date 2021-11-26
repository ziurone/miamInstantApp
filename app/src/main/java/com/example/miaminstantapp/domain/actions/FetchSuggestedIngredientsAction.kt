package com.example.miaminstantapp.domain.actions

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
            .countSuggested()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { c -> onCountSuggestedSuccess(c) }, ::onError)
            .track()
    }

    private fun onCountSuggestedSuccess(suggestedCount: Int) {
        if(suggestedCount < 3) {
            ingredientRepository
                .fetchFromServer(5 - suggestedCount)
                .flatMapCompletable { response -> ingredientRepository.addSuggestedIngredientsAssociations(response.ingredients) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( ::fetchSuggested , ::onError)
                .track()
        } else {
            fetchSuggested()
        }
    }

    private fun fetchSuggested() {
        ingredientRepository.getSuggestedIngredients()
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

    private fun onSuccess(listResponse: List<SuggestedIngredientWithVolumeUnits>) {
        liveData.value = IFetchSuggestedIngredientsAction.Result.Success(listResponse)
        cleanUp()
    }

}