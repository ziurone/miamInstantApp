package com.example.miaminstantapp.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.view.adapters.ArticlesAdapter
import com.example.miaminstantapp.viewmodel.ITicketArticlesViewModel
import kotlinx.android.synthetic.main.fragment_ticket_articles.*

class TicketArticlesFragment: BaseFragment<ITicketArticlesViewModel, ITicketArticlesViewModel.State>() {

    private lateinit var shopId: Number
    private lateinit var articlesAdapter: ArticlesAdapter

    companion object {
        const val SHOP_ID_KEY = "shopIdKey"
    }

    override fun getLayoutId(): Int = R.layout.fragment_ticket_articles

    override fun initViews() {
        arguments?.let {
            shopId = it.getInt(SHOP_ID_KEY, 0)
        }

        viewModel.fetch(shopId.toInt())

        articlesAdapter = ArticlesAdapter()
        articlesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter
        }

        super.initViews()
    }

    override fun onStateChanged(state: ITicketArticlesViewModel.State) {
        when(state) {
            is ITicketArticlesViewModel.State.FetchArticlesSuccess -> showArticles(state.articles)
        }
    }

    private fun showArticles(articles: List<ShopArticleEntity>) {
        articlesAdapter.setData(articles)
    }
}