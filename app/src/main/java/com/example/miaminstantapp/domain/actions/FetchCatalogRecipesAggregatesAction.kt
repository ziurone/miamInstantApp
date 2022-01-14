package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.repositories.CatalogRecipesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchCatalogRecipesAggregatesAction @Inject constructor(
    private val recipesRepository: CatalogRecipesRepository
):
    BaseAction<IFetchRecipesWithIngredientsAction.Result>(),
    IFetchRecipesWithIngredientsAction
{

    override fun fetch() {
        recipesRepository
            .fetchSearchRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchRecipesWithIngredientsAction.Result? {
        throw throwable
    }

    override fun getFailureResult(failedResponseCode: String): IFetchRecipesWithIngredientsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onSuccess(catalogRecipeAgreggates: List<CatalogRecipeAgreggate>) {
        liveData.value = IFetchRecipesWithIngredientsAction.Result.Success(catalogRecipeAgreggates)
    }
}