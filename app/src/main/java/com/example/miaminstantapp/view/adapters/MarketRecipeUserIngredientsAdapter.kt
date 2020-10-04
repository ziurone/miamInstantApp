package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredientEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_market_recipe_user_ingredient.*

class MarketRecipeUserIngredientsAdapter constructor(
): RecyclerView.Adapter<MarketRecipeUserIngredientsAdapter.ViewHolder>() {

    private val userIngredients = mutableListOf<DoableRecipeUserIngredientEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_market_recipe_user_ingredient, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userIngredients[position])
    }

    override fun getItemCount(): Int = userIngredients.size

    fun addIngredients(userIngredients: List<DoableRecipeUserIngredientEntity>) {
        this.userIngredients.addAll(userIngredients)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(userIngredient: DoableRecipeUserIngredientEntity) {
            name.text = userIngredient.ingredientName
        }
    }
}