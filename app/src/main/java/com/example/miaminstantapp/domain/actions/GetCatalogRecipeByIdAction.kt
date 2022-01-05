package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
import com.example.miaminstantapp.domain.repositories.ICatalogRecipesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetCatalogRecipeByIdAction @Inject constructor(
    private val catalogRecipesRepository: ICatalogRecipesRepository
):
    BaseAction<IGetCatalogRecipeByIdAction.Result>(),
    IGetCatalogRecipeByIdAction
{

    override fun getErrorResult(throwable: Throwable): IGetCatalogRecipeByIdAction.Result? {
        return IGetCatalogRecipeByIdAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IGetCatalogRecipeByIdAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetch(id: Int) {
        catalogRecipesRepository
            .fetchRecipeById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    fun onSuccess(recipeAggregate: CatalogRecipeAgreggate) {
        liveData.value = IGetCatalogRecipeByIdAction.Result.Success(recipeAggregate)
    }
}