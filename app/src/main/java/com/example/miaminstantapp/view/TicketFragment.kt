package com.example.miaminstantapp.view

import android.util.Log
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.ShopPurchase
import com.example.miaminstantapp.domain.relations.ShopWithBranchesAndArticles
import com.example.miaminstantapp.viewmodel.ITicketViewModel

class TicketFragment: BaseFragment<ITicketViewModel, ITicketViewModel.State>() {

    override fun initViews() {
        super.initViews()

        Log.i("TicketFragmet", "Enter Success")
    }

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        viewModel.fetch()
    }

    override fun getLayoutId(): Int = R.layout.fragment_ticket

    override fun onStateChanged(state: ITicketViewModel.State) {
        when(state) {
            is ITicketViewModel.State.FetchShopPurchasesSuccess -> showShopsWithTotal(state.shopsPurchases)
        }
    }

    private fun showShopsWithTotal(shopPurchases: List<ShopPurchase>) {
        shopPurchases.map {

            Log.i("SHOP_NAME", it.shop.name)
            Log.i("PRiCE", it.purchasePrice.toString())

        }
    }
}