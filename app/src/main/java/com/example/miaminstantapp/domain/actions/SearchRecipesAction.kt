package com.example.miaminstantapp.domain.actions

import android.util.Log
import com.example.miaminstantapp.domain.dtos.MarketIngredientDTO
import com.example.miaminstantapp.domain.dtos.toMarketRecipeEntity
import com.example.miaminstantapp.domain.repositories.IMarketIngredientRepository
import com.example.miaminstantapp.domain.repositories.IMarketRecipesRepository
import com.example.miaminstantapp.domain.repositories.MarketIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRecipesAction @Inject constructor(
    private val recipesRepository: IMarketRecipesRepository,
    private val marketIngredientRepository: IMarketIngredientRepository
): BaseAction<ISearchRecipesAction.Result>(), ISearchRecipesAction {

    override fun searchRecipes() {
        recipesRepository
            .search()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapCompletable { recipes ->

                recipesRepository.insertAll(recipes.map {
                    it.toMarketRecipeEntity()
                }).andThen{
                    val marketIngredients = mutableListOf<MarketIngredientDTO>()
                    recipes.map {
                        marketIngredients.addAll(it.marketIngredients)
                    }
                    marketIngredientRepository.insertAll(marketIngredients)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

            }
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
        Log.i("INGREDIENTS_INSERTED", "SUCCESS")
    }
}