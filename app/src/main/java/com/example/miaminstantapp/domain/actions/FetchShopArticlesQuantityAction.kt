package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.repositories.ShopArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchShopArticlesQuantityAction @Inject constructor(
    private val shopArticleRepository: ShopArticleRepository
): BaseAction<IFetchShopArticlesQuantityAction.Result>(), IFetchShopArticlesQuantityAction {

    override fun fetchQuantity() {
        shopArticleRepository
            .getQuantityInShoppingCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchShopArticlesQuantityAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchShopArticlesQuantityAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onSuccess(quantity: Int) {
        liveData.value = IFetchShopArticlesQuantityAction.Result.Success(quantity)
    }
}