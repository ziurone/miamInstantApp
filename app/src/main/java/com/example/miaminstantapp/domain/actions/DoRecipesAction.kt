package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.repositories.IMarketRecipesRepository
import com.example.miaminstantapp.domain.repositories.IShopArticleRepository
import com.example.miaminstantapp.domain.repositories.IUserMoneyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DoRecipesAction @Inject constructor(
    private val shopArticleRepository: IShopArticleRepository,
    private val moneyRepository: IUserMoneyRepository,
    private val marketRecipesRepository: IMarketRecipesRepository
): BaseAction<IDoRecipesAction.Result>(), IDoRecipesAction {

    override fun doRecipes() {
        shopArticleRepository
            .cleanPurchase()
            .andThen(shopArticleRepository.getPurchaseMoney())
            .flatMapCompletable { purchaseMoney -> moneyRepository.restMoney(purchaseMoney) }
            .andThen(marketRecipesRepository.deleteAll())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = IDoRecipesAction.Result.Success
    }

    override fun getErrorResult(throwable: Throwable): IDoRecipesAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IDoRecipesAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}