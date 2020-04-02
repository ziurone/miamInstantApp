package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation
import com.example.miaminstantapp.domain.repositories.IShopRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchShopsPurchaseAction @Inject constructor(
    private val shopRepository: IShopRepository
):
    BaseAction<IFetchShopsPurchaseAction.Result>(),
    IFetchShopsPurchaseAction {

    override fun fetch() {
        shopRepository
            .fetchShopsPurchase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchShopsPurchaseAction.Result? {
        return IFetchShopsPurchaseAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IFetchShopsPurchaseAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onSuccess(shopPurchases: List<ShopPurchaseRelation>) {
        liveData.value = IFetchShopsPurchaseAction.Result.Success(shopPurchases)
    }
}