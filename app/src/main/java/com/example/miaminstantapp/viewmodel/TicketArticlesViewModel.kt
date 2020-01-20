package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.FetchShopArticlesByShopAction
import com.example.miaminstantapp.domain.actions.IFetchShopArticlesAction
import javax.inject.Inject

class TicketArticlesViewModel @Inject constructor(
    private val fetchShopArticlesByShopAction: FetchShopArticlesByShopAction
): ITicketArticlesViewModel() {

    init {
        listenSource(fetchShopArticlesByShopAction.getLiveData(), ::onFetchArticlesResult)
    }

    override fun fetch(shopId: Int) {

    }

    private fun onFetchArticlesResult(result: IFetchShopArticlesAction.Result) {
        when(result) {
            is IFetchShopArticlesAction.Result.Success -> setState(State.FetchArticlesSuccess(result.articles))
        }
    }
}