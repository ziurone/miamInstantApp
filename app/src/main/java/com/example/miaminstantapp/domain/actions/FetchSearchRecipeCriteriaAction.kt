package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.repositories.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import io.reactivex.functions.Function5
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchSearchRecipeCriteriaAction @Inject constructor(
    private val ingredientRepository: IngredientRepository,
    private val moneyRepository: IUserMoneyRepository,
    private val branchesRepository: IBranchRepository,
    private val excludedIngredientsRepository: IExcludedIngredientRepository,
    private val recipesRepository: CatalogRecipesRepository,
    private val recipeBookRepository: RecipeBookRepository
): BaseAction<IFetchSearchRecipeCriteriaAction.Result>(), IFetchSearchRecipeCriteriaAction {

    override fun fetch() {
        val userIngredientsSingle = ingredientRepository.getUserIngredients()

        val userMoneySingle = moneyRepository.getUserMoney()

        val branchesSingle = Single.just(listOf<BranchEntity>())

        val excludedIngredientSingle = excludedIngredientsRepository.fetchAll()

        val excludedRecipesIdsSingle = recipeBookRepository.fetchRecipeBookRecipesIds()

        // TODO Add fetch RecipesIds to exclude.

        Single.zip(userIngredientsSingle, userMoneySingle, branchesSingle, excludedIngredientSingle, excludedRecipesIdsSingle,
            Function5<List<UserIngredientEntity>, Int, List<BranchEntity>, List<ExcludedIngredientEntity>, List<Int>, IFetchSearchRecipeCriteriaAction.Result.Success> {
                userIngredients: List<UserIngredientEntity>, userMoney: Int, branches: List<BranchEntity>, excludedIngredients: List<ExcludedIngredientEntity>, excludedRecipesIds: List<Int> ->
                    IFetchSearchRecipeCriteriaAction.Result.Success(RecipeSearchCriteria(
                        userIngredients,
                        userMoney,
                        branches,
                        excludedIngredients,
                        excludedRecipesIds
                    ))
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successResult -> liveData.value = successResult
            }, ::onError)
            .track()

    }

    override fun getErrorResult(throwable: Throwable): IFetchSearchRecipeCriteriaAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchSearchRecipeCriteriaAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}