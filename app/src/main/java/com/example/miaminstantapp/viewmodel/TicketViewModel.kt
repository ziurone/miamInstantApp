package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IDoPurchaseAction
import com.example.miaminstantapp.domain.actions.IFetchShopArticlesQuantityAction
import com.example.miaminstantapp.domain.actions.IFetchShopsPurchaseAction
import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import javax.inject.Inject

class TicketViewModel @Inject constructor(
    private val fetchShopsPurchaseAction: IFetchShopsPurchaseAction,
    private val doPurchaseAction: IDoPurchaseAction,
    private val fetchShopArticlesQuantityAction: IFetchShopArticlesQuantityAction
) : ITicketViewModel() {

    init {
        listenSource(fetchShopsPurchaseAction.getLiveData(), ::onFetchShopArticlesResult)
        listenSource(doPurchaseAction.getLiveData(), ::onDoPurchaseResult)
        listenSource(fetchShopArticlesQuantityAction.getLiveData(), ::onFetchShopArticlesResult)
    }

    private fun onFetchShopArticlesResult(result: IFetchShopArticlesQuantityAction.Result) {
        when(result) {
            is IFetchShopArticlesQuantityAction.Result.Success -> if (result.quantity > 0) setState(State.PurchaseHaveArticles) else setState(State.PurchaseIsEmpty)
        }
    }

    private fun onDoPurchaseResult(result: IDoPurchaseAction.Result) {
        when(result) {
            is IDoPurchaseAction.Result.Success -> setState(State.DoPurchaseSuccess)
        }
    }

    private fun onFetchShopArticlesResult(result: IFetchShopsPurchaseAction.Result) {
        when (result) {
            is IFetchShopsPurchaseAction.Result.Success -> showShopPurchases(result.shopPurchaes)
            is IFetchShopsPurchaseAction.Result.Error -> TODO()
        }
    }

    private fun showShopPurchases(shopPurchases: List<ShoppingListArticleEntity>) {
        setState(State.FetchShoppingListArticlesSuccess(shopPurchases))
    }

    override fun fetchShopPurchases() {
        fetchShopsPurchaseAction.fetch()
    }

    override fun doPurchase() {
        doPurchaseAction.doPurchase()
    }

    override fun fetchArticlesQuantity() = fetchShopArticlesQuantityAction.fetchQuantity()

}