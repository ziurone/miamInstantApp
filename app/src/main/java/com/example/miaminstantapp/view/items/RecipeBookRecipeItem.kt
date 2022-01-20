package com.example.miaminstantapp.view.items

import android.view.View
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.view.widgets.RecipeCardView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class RecipeBookRecipeItem(
    private val recipeBookRecipeEntity: RecipeBookRecipeEntity
): Item<RecipeBookRecipeItem.RecipeBookRecipeItemViewHolder>() {

    class RecipeBookRecipeItemViewHolder(view: View): GroupieViewHolder(view) {
        val recipeCard: RecipeCardView = view.findViewById(R.id.recipeCard)
    }

    override fun createViewHolder(itemView: View): RecipeBookRecipeItemViewHolder = RecipeBookRecipeItemViewHolder(itemView)

    override fun bind(viewHolder: RecipeBookRecipeItemViewHolder, position: Int) {
        viewHolder.recipeCard.setRecipeBookRecipe(recipeBookRecipeEntity)
    }

    override fun getLayout(): Int = R.layout.item_recipe_book_recipe
}