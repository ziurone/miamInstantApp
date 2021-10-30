package com.example.miaminstantapp.view.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class AutocompleteIngredientItem(
    private val ingredient: Ingredient,
    private val clickListener: (Ingredient) -> Unit
): Item<AutocompleteIngredientItem.AutocompleteIngredientItemViewHolder>() {

    class AutocompleteIngredientItemViewHolder(view: View): GroupieViewHolder(view) {
        val ingredientName: TextView = view.findViewById(R.id.name)
    }

    override fun createViewHolder(itemView: View) = AutocompleteIngredientItemViewHolder(itemView)

    override fun bind(viewHolder: AutocompleteIngredientItemViewHolder, position: Int) {
        viewHolder.ingredientName.text = ingredient.name
        viewHolder.itemView.setOnClickListener {
            clickListener(ingredient)
        }
    }

    override fun getLayout(): Int = R.layout.ingredient_item_autocomplete
}