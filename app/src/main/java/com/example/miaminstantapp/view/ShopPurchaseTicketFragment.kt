package com.example.miaminstantapp.view

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation
import com.example.miaminstantapp.view.adapters.ShopPurchaseAdapter
import com.example.miaminstantapp.viewmodel.ITicketViewModel
import kotlinx.android.synthetic.main.fragment_shop_purchase_ticket.*

class ShopPurchaseTicketFragment: BaseFragment<ITicketViewModel, ITicketViewModel.State>(), ShopPurchaseAdapter.OnShopPurchaseItemClickListener {

    private lateinit var shopPurchaseAdapter: ShopPurchaseAdapter

    override fun initViews() {
        super.initViews()

        doPurchaseButton.isEnabled = false

        shopPurchaseAdapter = ShopPurchaseAdapter(this)
        shopPurchasesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shopPurchaseAdapter
        }

        doPurchaseButton.setOnClickListener {
            viewModel.doPurchase()
        }
    }

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        viewModel.fetchArticlesQuantity()
    }

    override fun getLayoutId(): Int = R.layout.fragment_shop_purchase_ticket

    override fun onStateChanged(state: ITicketViewModel.State) {
        when(state) {
            is ITicketViewModel.State.PurchaseHaveArticles -> fetchShopPurchases()
            is ITicketViewModel.State.PurchaseIsEmpty -> showEmptyView()
            is ITicketViewModel.State.FetchShopPurchasesSuccess -> showShopPurchases(state.shopsPurchases)
            is ITicketViewModel.State.DoPurchaseSuccess -> recipesDone()
        }
    }

    private fun showEmptyView() {
        shoppingCartEmptyMessage.isVisible = true
        doPurchaseButton.isEnabled = false
    }

    private fun fetchShopPurchases() {
        shoppingCartEmptyMessage.isVisible = false
        viewModel.fetchShopPurchases()
    }

    private fun recipesDone() {
        findNavController().navigate(R.id.global_action_toRecipeBookFragment)
    }

    private fun showShopPurchases(shopPurchases: List<ShopPurchaseRelation>) {
        shopPurchaseAdapter.setData(shopPurchases)
        doPurchaseButton.isEnabled = true
        doPurchaseButton.isVisible = true
    }

    override fun onClick(shopPurchase: ShopPurchaseRelation) {
        val bundle = bundleOf(
            TicketArticlesFragment.SHOP_ID_KEY to shopPurchase.shop.shopId
        )

        findNavController().navigate(R.id.action_shopPurchaseTicket_to_articlesTicket, bundle)
    }
}