package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_market_recipe_user_ingredient.*

class MarketRecipeMarketIngredientsAdapter constructor(
): RecyclerView.Adapter<MarketRecipeMarketIngredientsAdapter.ViewHolder>() {

    private val marketIngredients = mutableListOf<MarketIngredientEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_market_recipe_market_ingredient, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(marketIngredients[position])
    }

    override fun getItemCount(): Int = marketIngredients.size

    fun addIngredients(userIngredients: List<MarketIngredientEntity>) {
        this.marketIngredients.addAll(userIngredients)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(marketIngredient: MarketIngredientEntity) {
            ingredientName.text = marketIngredient.ingredientName
        }
    }
}