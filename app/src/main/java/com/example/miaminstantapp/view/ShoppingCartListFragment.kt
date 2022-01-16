package com.example.miaminstantapp.view

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation
import com.example.miaminstantapp.view.adapters.ShopPurchaseAdapter
import com.example.miaminstantapp.view.items.ShoppingCartListItem
import com.example.miaminstantapp.viewmodel.IShoppingCartListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_shop_purchase_ticket.*

class ShoppingCartListFragment: BaseFragment<IShoppingCartListViewModel, IShoppingCartListViewModel.State>(), ShopPurchaseAdapter.OnShopPurchaseItemClickListener {

    private val shopPurchaseAdapter = GroupAdapter<GroupieViewHolder>()

    override fun initViews() {
        super.initViews()

        doPurchaseButton.isEnabled = false

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
        fetchShopPurchases()
    }

    override fun getLayoutId(): Int = R.layout.fragment_shop_purchase_ticket

    override fun onStateChanged(state: IShoppingCartListViewModel.State) {
        when(state) {
            is IShoppingCartListViewModel.State.PurchaseHaveArticles -> throw Exception("Deprecated method purchase have articles.")
            is IShoppingCartListViewModel.State.PurchaseIsEmpty -> showEmptyView()
            is IShoppingCartListViewModel.State.FetchShoppingListArticlesSuccess -> showShopPurchases(state.shopsPurchases)
            is IShoppingCartListViewModel.State.DoPurchaseSuccess -> navigateToRecipeBook()
            is IShoppingCartListViewModel.State.Error -> TODO()
        }
    }

    private fun showEmptyView() {
        shoppingCartEmptyMessage.isVisible = true
        doPurchaseButton.isEnabled = false
    }

    private fun fetchShopPurchases() {
        viewModel.fetchShopPurchases()
    }

    private fun navigateToRecipeBook() {
        findNavController().navigate(R.id.global_action_toRecipeBookFragment)
    }

    private fun showShopPurchases(shopPurchases: List<ShoppingListArticleEntity>) {
        if(shopPurchases.isEmpty()) {
            shoppingCartEmptyMessage.isVisible = true
            doPurchaseButton.isEnabled = false
        } else {
            shoppingCartEmptyMessage.isVisible = false
            shopPurchaseAdapter.update(shopPurchases.map { ShoppingCartListItem(it) })
            doPurchaseButton.isEnabled = true
            doPurchaseButton.isVisible = true
        }
    }

    override fun onClick(shopPurchase: ShopPurchaseRelation) {
    }
}