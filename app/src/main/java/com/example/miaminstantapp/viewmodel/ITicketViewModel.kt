package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation

abstract class ITicketViewModel: BaseViewModel<ITicketViewModel.State>() {
    sealed class State {
        object PurchaseHaveArticles: State()
        data class FetchShoppingListArticlesSuccess(val shopsPurchases: List<ShoppingListArticleEntity>): State()
        data class Error(val errorMessage: String): State()
        object DoPurchaseSuccess: State()
        object PurchaseIsEmpty: State()
    }

    abstract fun fetchShopPurchases()
    abstract fun doPurchase()
    abstract fun fetchArticlesQuantity()

}