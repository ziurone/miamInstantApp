package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.DoableRecipe
import com.example.miaminstantapp.domain.repositories.IMarketRecipesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetDoableRecipeByIdAction @Inject constructor(
    private val marketRecipesRepository: IMarketRecipesRepository
):
    BaseAction<IGetDoableRecipeByIdAction.Result>(),
    IGetDoableRecipeByIdAction
{

    override fun getErrorResult(throwable: Throwable): IGetDoableRecipeByIdAction.Result? {
        return IGetDoableRecipeByIdAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IGetDoableRecipeByIdAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchRecipeById(id: Int) {
        marketRecipesRepository
            .fetchRecipeById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    fun onSuccess(recipe: DoableRecipe) {
        liveData.value = IGetDoableRecipeByIdAction.Result.Success(recipe)
    }
}