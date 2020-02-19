package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.repositories.IBranchRepository
import com.example.miaminstantapp.domain.repositories.IUserMoneyRepository
import com.example.miaminstantapp.domain.repositories.IngredientRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchSearchRecipeCriteriaAction @Inject constructor(
    private val ingredientRepository: IngredientRepository,
    private val moneyRepository: IUserMoneyRepository,
    private val branchesRepository: IBranchRepository
): BaseAction<IFetchSearchRecipeCriteriaAction.Result>(), IFetchSearchRecipeCriteriaAction {

    override fun fetch() {
        val userIngredientsSingle = ingredientRepository.getUserIngredients()

        val userMoneySingle = moneyRepository.getUserMoney()

        val branchesSingle = branchesRepository.fetchAllFromLocal()


        Single.zip(userIngredientsSingle, Single.fromObservable(userMoneySingle), branchesSingle,
            Function3<List<UserIngredientEntity>, Int, List<BranchEntity>, IFetchSearchRecipeCriteriaAction.Result.Success> {
                userIngredients: List<UserIngredientEntity>, userMoney: Int, branches: List<BranchEntity> ->
                    IFetchSearchRecipeCriteriaAction.Result.Success(RecipeSearchCriteria(
                        userIngredients,
                        userMoney,
                        branches
                    ))
            }).subscribeOn(Schedulers.io())
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