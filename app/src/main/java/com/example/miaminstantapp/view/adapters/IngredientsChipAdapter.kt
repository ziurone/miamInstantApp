package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.dtos.Ingredient
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_suggested_ingredient.*

class IngredientsChipAdapter constructor(
    private val clickListener: IngredientItemClickListener
): RecyclerView.Adapter<IngredientsChipAdapter.IngredientViewHolder>() {

    private val suggestedIngredientsList = mutableListOf<Ingredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_suggested_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(suggestedIngredientsList[position], position)
    }

    override fun getItemCount(): Int = suggestedIngredientsList.size

    fun setData(ingredients: List<Ingredient>) {
        this.suggestedIngredientsList.addAll(ingredients)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        suggestedIngredientsList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, suggestedIngredientsList.size)
    }

    inner class IngredientViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(ingredient: Ingredient, position: Int) {
            ingredientChip.text = ingredient.name

            ingredientChip.setOnClickListener {
                clickListener.onClick(ingredient)
                removeAt(position)
            }
        }
    }

    interface IngredientItemClickListener {
        fun onClick(ingredient: Ingredient)
    }
}