package com.example.miaminstantapp.view.recipedetail.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.MarketIngredientRelations
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CatalogRecipeMarketIngredientItem(
    private val marketIngredientEntity: MarketIngredientRelations,
    private val clickListener: (MarketIngredientRelations) -> Unit
): Item<CatalogRecipeMarketIngredientItem.CatalogRecipeMarketIngredientItemViewHolder>() {

    class CatalogRecipeMarketIngredientItemViewHolder(view: View): GroupieViewHolder(view) {
        val ingredientName: TextView = view.findViewById(R.id.ingredientWithQuantity)
        val haveIngredient: TextView = view.findViewById(R.id.haveIngredientButton)
    }

    override fun getLayout(): Int = R.layout.item_catalog_recipe_market_ingredient

    override fun createViewHolder(itemView: View): CatalogRecipeMarketIngredientItemViewHolder = CatalogRecipeMarketIngredientItemViewHolder(itemView)

    override fun bind(viewHolder: CatalogRecipeMarketIngredientItemViewHolder, position: Int) {
        viewHolder.ingredientName.text =  marketIngredientEntity.volumeUnit.name + " " +  marketIngredientEntity.marketIngredient.name
        viewHolder.haveIngredient.setOnClickListener {
            clickListener(marketIngredientEntity)
        }
    }
}