package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.entities.ShopArticleEntity

abstract class ITicketArticlesViewModel: BaseViewModel<ITicketArticlesViewModel.State>() {
    sealed class State {
        data class FetchArticlesSuccess(val articles: List<ShopArticleEntity>): State()
        data class Error(val message: String): State()
    }

    abstract fun fetch(shopId: Int)
}