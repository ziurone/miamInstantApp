package com.example.miaminstantapp.domain.actions.recipebook

import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.domain.actions.IFetchRecipeBookRecipesAction
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.domain.repositories.IRecipeBookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchRecipeBookRecipesAction @Inject constructor(
    private val recipeBookRepository: IRecipeBookRepository
): BaseAction<IFetchRecipeBookRecipesAction.Result>(), IFetchRecipeBookRecipesAction
{
    override fun fetch(made: Boolean) {
        recipeBookRepository
            .fetchRecipesDesc(made)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchRecipeBookRecipesAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchRecipeBookRecipesAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess(recipes: List<RecipeBookRecipeEntity>) {
        liveData.value = IFetchRecipeBookRecipesAction.Result.Success(recipes)
    }
}