package com.example.miaminstantapp.domain.actions

import android.util.Log
import com.example.miaminstantapp.domain.entities.*
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import com.example.miaminstantapp.domain.relations.toRecipeBookRecipe
import com.example.miaminstantapp.domain.repositories.IRecipeBookRecipeIngredientRepository
import com.example.miaminstantapp.domain.repositories.IRecipeBookRepository
import com.example.miaminstantapp.domain.repositories.IShopArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddRecipeAction @Inject constructor(
    private val shopArticleRepository: IShopArticleRepository,
    private val recipeBookRepository: IRecipeBookRepository,
    private val recipeBookRecipeIngredientRepository: IRecipeBookRecipeIngredientRepository
): BaseAction<IAddRecipeAction.Result>(), IAddRecipeAction {

    override fun addRecipe(recipe: CatalogRecipeRelations) {
        shopArticleRepository
            .insertAll(recipe.marketIngredients.map { it.marketIngredient.toShopArticle() })
            .andThen(recipeBookRepository.addRecipe(recipe.toRecipeBookRecipe()))
            .andThen(recipeBookRecipeIngredientRepository.addRecipeIngredients(recipe.userIngredients.map {
                recipeUserIngredient -> recipeUserIngredient.toRecipeBookIngredient(recipe.recipe.id)
            }))
            .andThen(recipeBookRecipeIngredientRepository.addRecipeIngredients(recipe.marketIngredients.map {
                it -> it.marketIngredient.toRecipeBookIngredient(recipe.recipe.id)
            }))
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