package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.dtos.Ingredient
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user_ingredient_autocomplete.name

class AutocompleteUserIngredientsAdapter(
    private val itemClickListener: OnAddSearchedIngredient
):
    RecyclerView.Adapter<AutocompleteUserIngredientsAdapter.ViewHolder>() {

    private var ingredients: List<Ingredient> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item_autocomplete, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    fun setData(ingredientList: List<Ingredient>) {
        this.ingredients = ingredientList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int  = ingredients.size

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(ingredient: Ingredient) {
            name.text = ingredient.name.capitalize()

            containerView.setOnClickListener {
                itemClickListener.onItemClick(ingredient)
            }
        }
    }

    interface OnAddSearchedIngredient {
        fun onItemClick(ingredient: Ingredient)
    }

}