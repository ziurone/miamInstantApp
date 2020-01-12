package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IFetchShopArticlesAction
import com.example.miaminstantapp.domain.relations.ShopWithBranchesAndArticles
import javax.inject.Inject

class TicketViewModel @Inject constructor(
    private val fetchShopArticlesAction: IFetchShopArticlesAction
) : ITicketViewModel() {

    init {
        listenSource(fetchShopArticlesAction.getLiveData(), ::onFetchShopArticlesResult)
    }

    private fun onFetchShopArticlesResult(result: IFetchShopArticlesAction.Result) {
        when (result) {
            is IFetchShopArticlesAction.Result.Success -> orderArticlesByShop(result.shopsWithArticles)
        }
    }

    private fun orderArticlesByShop(articles: List<ShopWithBranchesAndArticles>) {
        setState(State.FetchShopArticlesSuccess(articles))
    }

    override fun fetch() {
        fetchShopArticlesAction.fetch()
    }

}