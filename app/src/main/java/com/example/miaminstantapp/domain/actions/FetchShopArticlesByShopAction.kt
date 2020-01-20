package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.domain.repositories.IShopArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchShopArticlesByShopAction @Inject constructor(
    private val shopArticleRepository: IShopArticleRepository
) : BaseAction<IFetchShopArticlesAction.Result>(),
    IFetchShopArticlesAction
{
    override fun fetch() {
        shopArticleRepository
            .fetch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchShopArticlesAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchShopArticlesAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess(shopArticles: List<ShopArticleEntity>) {
        liveData.value = IFetchShopArticlesAction.Result.Success(shopArticles)
    }
}