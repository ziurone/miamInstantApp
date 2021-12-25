package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.repositories.ICatalogRecipesRepository
import com.example.miaminstantapp.domain.repositories.IShopArticleRepository
import com.example.miaminstantapp.domain.repositories.IUserMoneyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Executes purchase.
 * Rest puchase money to user money.
 * Remove MarketsRecipe.
 *
 *
 */
class DoPurchaseAction @Inject constructor(
    private val shopArticleRepository: IShopArticleRepository,
    private val moneyRepository: IUserMoneyRepository,
    private val catalogRecipesRepository: ICatalogRecipesRepository
): BaseAction<IDoPurchaseAction.Result>(), IDoPurchaseAction {

    override fun doPurchase() {
        shopArticleRepository
            .getPurchaseMoney()
            .flatMapCompletable { purchaseMoney -> moneyRepository.restMoney(purchaseMoney) }
            .andThen(catalogRecipesRepository.deleteAll())
            .andThen(shopArticleRepository.cleanPurchase())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = IDoPurchaseAction.Result.Success
    }

    override fun getErrorResult(throwable: Throwable): IDoPurchaseAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IDoPurchaseAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}