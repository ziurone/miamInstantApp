package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.domain.repositories.IShopArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchShopArticlesByShopByShopAction @Inject constructor(
    private val shopArticleRepository: IShopArticleRepository
) : BaseAction<IFetchShopArticlesByShopAction.Result>(),
    IFetchShopArticlesByShopAction
{
    override fun fetchByShop(shopId: Int) {
        shopArticleRepository
            .fetchByShopId(shopId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchShopArticlesByShopAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchShopArticlesByShopAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess(shopArticles: List<ShopArticleEntity>) {
        liveData.value = IFetchShopArticlesByShopAction.Result.Success(shopArticles)
    }
}