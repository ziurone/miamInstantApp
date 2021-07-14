package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTO
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.dtos.toMarketRecipeEntity
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.repositories.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRecipesAction @Inject constructor(
    private val recipesRepository: IMarketRecipesRepository,
    private val marketIngredientRepository: IMarketIngredientRepository,
    private val userRecipeIngredientRepository: IUserRecipeIngredientRepository
): BaseAction<ISearchRecipesAction.Result>(), ISearchRecipesAction {

    override fun searchRecipes(searchCriteria: RecipeSearchCriteria) {
        recipesRepository
            .search(searchCriteria)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { recipes ->
                recipesRepository.insertAll(recipes.map {
                    it.toMarketRecipeEntity()
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingleDefault(recipes)
            }
            .flatMap { recipes ->
                val marketIngredients = mutableListOf<MarketIngredientDTO>()
                recipes.map {
                    marketIngredients.addAll(it.marketIngredients)
                }
                marketIngredientRepository
                    .insertAll(marketIngredients)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .toSingleDefault(recipes)
            }
            .flatMapCompletable { recipes ->
                val userIngredientsEntities = mutableListOf<CatalogRecipeUserIngredientEntity>()

                recipes.map {
                    val r = it
                    userIngredientsEntities.addAll(
                        it.userIngredients.map { ingDTO ->
                            CatalogRecipeUserIngredientEntity(
                                ingredientId = ingDTO.id,
                                ingredientName = ingDTO.name,
                                usedQuantity = ingDTO.usedQuantity,
                                volumeUnitId = ingDTO.volumeUnitId,
                                volumeUnitQuantity = ingDTO.volumeUnitQuantity,
                                recipeId = r.id
                            )
                        }
                    )
                }

                userRecipeIngredientRepository
                    .insertAll(userIngredientsEntities)
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
        liveData.value = ISearchRecipesAction.Result.Success
    }
}