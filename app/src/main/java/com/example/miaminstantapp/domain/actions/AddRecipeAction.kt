package com.example.miaminstantapp.domain.actions

import android.util.Log
import com.example.miaminstantapp.domain.entities.*
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
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

    override fun addRecipe(catalogRecipeAggregate: CatalogRecipeAgreggate) {
        shopArticleRepository
            .insertAll(catalogRecipeAggregate.marketIngredients.map { it.marketIngredient.toShoppingArticle() })
            .andThen(recipeBookRepository.addRecipe(catalogRecipeAggregate.toRecipeBookRecipe()))
            .andThen(recipeBookRecipeIngredientRepository.addRecipeIngredients(catalogRecipeAggregate.userIngredients.map {
                recipeUserIngredient -> recipeUserIngredient.userIngredient.toRecipeBookIngredient(catalogRecipeAggregate.recipe.id)
            }))
            .andThen(recipeBookRecipeIngredientRepository.addRecipeIngredients(catalogRecipeAggregate.marketIngredients.map {
                it.marketIngredient.toRecipeBookIngredient()
            }))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({onSuccess(catalogRecipeAggregate)}, ::onError)
            .track()
    }

    override fun getFailureResult(failedResponseCode: String): IAddRecipeAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorResult(throwable: Throwable): IAddRecipeAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onSuccess(catalogRecipeAggregate: CatalogRecipeAgreggate) {
        Log.i("ARTICLES_INSERTED", "SUCCESS")
        liveData.value = IAddRecipeAction.Result.Success(catalogRecipeAggregate.marketIngredients.isNotEmpty())
    }

}