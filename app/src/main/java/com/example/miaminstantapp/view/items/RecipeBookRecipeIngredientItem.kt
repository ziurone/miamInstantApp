package com.example.miaminstantapp.view.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class RecipeBookRecipeIngredientItem(
    private val recipeBookRecipeIngredientEntity: RecipeBookRecipeIngredientEntity
): Item<RecipeBookRecipeIngredientItem.RecipeBookRecipeIngredientItemViewHolder>() {


    class RecipeBookRecipeIngredientItemViewHolder(view: View) : GroupieViewHolder(view) {
        val ingredientDescription : TextView = view.findViewById(R.id.ingredientDescription)
    }

    override fun createViewHolder(itemView: View): RecipeBookRecipeIngredientItemViewHolder = RecipeBookRecipeIngredientItemViewHolder(itemView)

    override fun bind(viewHolder: RecipeBookRecipeIngredientItemViewHolder, position: Int) {
        viewHolder.ingredientDescription.text = recipeBookRecipeIngredientEntity.name
    }

    override fun getLayout(): Int = R.layout.item_recipe_book_recipe_ingredient
}