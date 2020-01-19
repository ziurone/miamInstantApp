package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.FetchShopsPurchaseAction
import com.example.miaminstantapp.domain.actions.IFetchShopArticlesAction
import com.example.miaminstantapp.domain.actions.IFetchShopsPurchaseAction
import com.example.miaminstantapp.domain.relations.ShopPurchase
import javax.inject.Inject

class TicketViewModel @Inject constructor(
    private val fetchShopsPurchaseAction: IFetchShopsPurchaseAction
) : ITicketViewModel() {

    init {
        listenSource(fetchShopsPurchaseAction.getLiveData(), ::onFetchShopArticlesResult)
    }

    private fun onFetchShopArticlesResult(result: IFetchShopsPurchaseAction.Result) {
        when (result) {
            is IFetchShopsPurchaseAction.Result.Success -> showShopPurchases(result.shopPurchaes)
        }
    }

    private fun showShopPurchases(shopPurchases: List<ShopPurchase>) {
        setState(State.FetchShopPurchasesSuccess(shopPurchases))
    }

    override fun fetch() {
        fetchShopsPurchaseAction.fetch()
    }

}