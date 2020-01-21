package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation

abstract class ITicketViewModel: BaseViewModel<ITicketViewModel.State>() {
    sealed class State {
        data class FetchShopPurchasesSuccess(val shopsPurchases: List<ShopPurchaseRelation>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetch()

}