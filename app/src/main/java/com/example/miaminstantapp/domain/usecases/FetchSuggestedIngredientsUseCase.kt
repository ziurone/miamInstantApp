package com.example.miaminstantapp.domain.usecases

import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FetchSuggestedIngredientsUseCase constructor(
    private val ingredientRepository: IIngredientRepository
) : IFetchSuggestedIngredientsUseCase, BaseUseCase<IFetchSuggestedIngredientsUseCase.Result>()
{

    override fun fetch() {
        ingredientRepository
            .getSuggestedIngredients()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchSuggestedIngredientsUseCase.Result? {
        return IFetchSuggestedIngredientsUseCase.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IFetchSuggestedIngredientsUseCase.Result? {
        return IFetchSuggestedIngredientsUseCase.Result.Error(failedResponseCode)
    }

    private fun onSuccess(response: SuggestedIngredientsResponse) {
        liveData.value = IFetchSuggestedIngredientsUseCase.Result.Success(response)
        cleanUp()
    }

}