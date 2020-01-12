package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.ShopWithBranchesAndArticles

abstract class ITicketViewModel: BaseViewModel<ITicketViewModel.State>() {
    sealed class State {
        data class FetchShopArticlesSuccess(val shops: List<ShopWithBranchesAndArticles>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetch()

}