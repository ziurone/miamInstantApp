package com.example.miaminstantapp.view.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ShoppingCartListItem(
    private val shoppingListArticleEntity: ShoppingListArticleEntity
): Item<ShoppingCartListItem.ShoppingCartListItemViewHolder>() {

    class ShoppingCartListItemViewHolder(view: View): GroupieViewHolder(view) {
        val articleDescription : TextView = view.findViewById(R.id.articleDescription)
    }

    override fun createViewHolder(itemView: View): ShoppingCartListItemViewHolder = ShoppingCartListItemViewHolder(itemView)

    override fun bind(viewHolder: ShoppingCartListItemViewHolder, position: Int) {
        viewHolder.articleDescription.text = shoppingListArticleEntity.ingredientName
    }

    override fun getLayout(): Int = R.layout.item_shopping_cart_list

}