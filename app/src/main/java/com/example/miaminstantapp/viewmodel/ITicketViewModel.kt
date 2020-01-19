package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.ShopPurchase

abstract class ITicketViewModel: BaseViewModel<ITicketViewModel.State>() {
    sealed class State {
        data class FetchShopPurchasesSuccess(val shopsPurchases: List<ShopPurchase>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetch()

}