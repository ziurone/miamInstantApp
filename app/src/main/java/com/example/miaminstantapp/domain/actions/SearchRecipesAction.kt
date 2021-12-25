package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTOLegacy
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.repositories.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRecipesAction @Inject constructor(
    private val recipesRepository: ICatalogRecipesRepository
): BaseAction<ISearchRecipesAction.Result>(), ISearchRecipesAction {

    override fun searchRecipes(searchCriteria: RecipeSearchCriteria) {
        recipesRepository
            .search(searchCriteria)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapCompletable { recipes -> recipesRepository.insertAll(recipes) }
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): ISearchRecipesAction.Result? {
        return ISearchRecipesAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): ISearchRecipesAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess() {
        liveData.value = ISearchRecipesAction.Result.Success
    }
}