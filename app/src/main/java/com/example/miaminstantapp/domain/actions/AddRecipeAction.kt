package com.example.miaminstantapp.domain.actions

import android.util.Log
import com.example.miaminstantapp.domain.entities.*
import com.example.miaminstantapp.domain.relations.DoableRecipe
import com.example.miaminstantapp.domain.relations.toRecipeBookRecipe
import com.example.miaminstantapp.domain.repositories.IRecipeBookRepository
import com.example.miaminstantapp.domain.repositories.IShopArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddRecipeAction @Inject constructor(
    private val shopArticleRepository: IShopArticleRepository,
    private val recipeBookRepository: IRecipeBookRepository
): BaseAction<IAddRecipeAction.Result>(), IAddRecipeAction {

    override fun addRecipe(recipe: DoableRecipe) {
        shopArticleRepository
            .insertAll(recipe.marketIngredients.map { it.toShopArticle() })
            .andThen(recipeBookRepository.addRecipe(recipe.toRecipeBookRecipe()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getFailureResult(failedResponseCode: String): IAddRecipeAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorResult(throwable: Throwable): IAddRecipeAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onSuccess() {
        Log.i("ARTICLES_INSERTED", "SUCCESS")
        liveData.value = IAddRecipeAction.Result.Success
    }

}