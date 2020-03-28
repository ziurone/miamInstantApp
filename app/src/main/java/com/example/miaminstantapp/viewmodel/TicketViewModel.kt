package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IDoPurchaseAction
import com.example.miaminstantapp.domain.actions.IFetchShopsPurchaseAction
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation
import javax.inject.Inject

class TicketViewModel @Inject constructor(
    private val fetchShopsPurchaseAction: IFetchShopsPurchaseAction,
    private val doPurchaseAction: IDoPurchaseAction
) : ITicketViewModel() {

    init {
        listenSource(fetchShopsPurchaseAction.getLiveData(), ::onFetchShopArticlesResult)
        listenSource(doPurchaseAction.getLiveData(), ::onDoPurchaseResult)
    }


    private fun onDoPurchaseResult(result: IDoPurchaseAction.Result) {
        when(result) {
            is IDoPurchaseAction.Result.Success -> setState(State.DoPurchaseSuccess)
        }
    }

    private fun onFetchShopArticlesResult(result: IFetchShopsPurchaseAction.Result) {
        when (result) {
            is IFetchShopsPurchaseAction.Result.Success -> showShopPurchases(result.shopPurchaes)
        }
    }

    private fun showShopPurchases(shopPurchases: List<ShopPurchaseRelation>) {
        setState(State.FetchShopPurchasesSuccess(shopPurchases))
    }

    override fun fetch() {
        fetchShopsPurchaseAction.fetch()
    }

    override fun doPurchase() {
        doPurchaseAction.doPurchase()
    }

}