package com.example.miaminstantapp.view.items

import android.view.View
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.DoableRecipe
import com.example.miaminstantapp.view.widgets.RecipeCardView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class RecipeItem(
    private val doableRecipe: DoableRecipe,
    private val clickListener: () -> Unit
): Item<RecipeItem.RecipeItemViewHolder>() {

    override fun createViewHolder(itemView: View): RecipeItemViewHolder = RecipeItemViewHolder(itemView)

    override fun getLayout(): Int = R.layout.item_market_recipe

    override fun bind(viewHolder: RecipeItemViewHolder, position: Int) {
        val recipeCard = viewHolder.recipeCard
        recipeCard.setRecipe(doableRecipe)
        recipeCard.setOnClickListener {
            clickListener()
        }
    }

    class RecipeItemViewHolder(view: View): GroupieViewHolder(view) {
        val recipeCard: RecipeCardView = view.findViewById(R.id.recipeCard)
    }
}