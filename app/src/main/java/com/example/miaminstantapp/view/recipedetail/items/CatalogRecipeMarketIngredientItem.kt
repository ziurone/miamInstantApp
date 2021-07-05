package com.example.miaminstantapp.view.recipedetail.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import com.example.miaminstantapp.domain.relations.MarketIngredientRelations
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CatalogRecipeMarketIngredientItem(
    private val marketIngredientEntity: MarketIngredientRelations
): Item<CatalogRecipeMarketIngredientItem.CatalogRecipeMarketIngredientItemViewHolder>() {

    class CatalogRecipeMarketIngredientItemViewHolder(view: View): GroupieViewHolder(view) {
        val ingredientName: TextView = view.findViewById(R.id.ingredientWithQuantity)
        val articleName: TextView = view.findViewById(R.id.articleName)
    }

    override fun getLayout(): Int = R.layout.item_catalog_recipe_market_ingredient

    override fun createViewHolder(itemView: View): CatalogRecipeMarketIngredientItemViewHolder = CatalogRecipeMarketIngredientItemViewHolder(itemView)

    override fun bind(viewHolder: CatalogRecipeMarketIngredientItemViewHolder, position: Int) {
        viewHolder.articleName.text = marketIngredientEntity.marketIngredient.articleName
        viewHolder.ingredientName.text = marketIngredientEntity.marketIngredient.volumeUnitQuantity.toInt().toString() + " " + marketIngredientEntity.volumeUnit.name + " " +  marketIngredientEntity.marketIngredient.ingredientName
    }
}