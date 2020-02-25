package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.FetchShopArticlesByShopByShopAction
import com.example.miaminstantapp.domain.actions.IFetchShopArticlesByShopAction
import javax.inject.Inject

class TicketArticlesViewModel @Inject constructor(
    private val fetchShopArticlesByShopAction: FetchShopArticlesByShopByShopAction
): ITicketArticlesViewModel() {

    init {
        listenSource(fetchShopArticlesByShopAction.getLiveData(), ::onFetchArticlesResult)
    }

    override fun fetch(shopId: Int) {
        fetchShopArticlesByShopAction.fetchByShop(shopId)
    }

    private fun onFetchArticlesResult(result: IFetchShopArticlesByShopAction.Result) {
        when(result) {
            is IFetchShopArticlesByShopAction.Result.Success -> setState(State.FetchArticlesSuccess(result.articles))
        }
    }
}