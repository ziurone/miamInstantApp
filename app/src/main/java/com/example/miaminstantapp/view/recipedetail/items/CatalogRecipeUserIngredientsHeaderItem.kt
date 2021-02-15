package com.example.miaminstantapp.view.recipedetail.items

import com.example.miaminstantapp.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CatalogRecipeUserIngredientsHeaderItem: Item<GroupieViewHolder>() {
    override fun getLayout(): Int = R.layout.item_catalog_recipe_user_ingredients_header

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}