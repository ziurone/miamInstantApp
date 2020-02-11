package com.example.miaminstantapp.view

import android.util.Log
import androidx.core.os.bundleOf
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

        shopPurchaseAdapter = ShopPurchaseAdapter(this)
        shopPurchasesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shopPurchaseAdapter
        }
    }

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        viewModel.fetch()
    }

    override fun getLayoutId(): Int = R.layout.fragment_shop_purchase_ticket

    override fun onStateChanged(state: ITicketViewModel.State) {
        when(state) {
            is ITicketViewModel.State.FetchShopPurchasesSuccess -> showShopPurchases(state.shopsPurchases)
        }
    }

    private fun showShopPurchases(shopPurchases: List<ShopPurchaseRelation>) {
        shopPurchaseAdapter.setData(shopPurchases)
    }

    override fun onClick(shopPurchase: ShopPurchaseRelation) {
        val bundle = bundleOf(
            TicketArticlesFragment.SHOP_ID_KEY to shopPurchase.shop.shopId
        )

        findNavController().navigate(R.id.action_shopPurchaseTicket_to_articlesTicket, bundle)
    }
}