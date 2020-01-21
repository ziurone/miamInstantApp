package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_shop_purchase.*

class ShopPurchaseAdapter(
    private val clickListener: OnShopPurchaseItemClickListener
): RecyclerView.Adapter<ShopPurchaseAdapter.ViewHolder>() {

    private val shopPurchases = mutableListOf<ShopPurchaseRelation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_purchase, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shopPurchases[position])
    }

    override fun getItemCount(): Int = shopPurchases.size

    fun setData(shopPurchasesList: List<ShopPurchaseRelation>) {
        shopPurchases.addAll(shopPurchasesList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(shopPurchase: ShopPurchaseRelation) {
            shopName.text = shopPurchase.shop.name
            shopPurchasePrice.text = shopPurchase.purchasePrice.toString()

            containerView.setOnClickListener {
                clickListener.onClick(shopPurchase)
            }
        }
    }

    interface OnShopPurchaseItemClickListener {
        fun onClick(shopPurchase: ShopPurchaseRelation)
    }
}