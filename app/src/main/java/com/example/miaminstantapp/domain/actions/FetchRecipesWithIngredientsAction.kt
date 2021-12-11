package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import com.example.miaminstantapp.domain.repositories.CatalogRecipesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchRecipesWithIngredientsAction @Inject constructor(
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchRecipesWithIngredientsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onSuccess(catalogRecipeRelations: List<CatalogRecipeRelations>) {
        liveData.value = IFetchRecipesWithIngredientsAction.Result.Success(catalogRecipeRelations)
    }
}