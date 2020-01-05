package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeWithUserIngredients
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_doable_recipe.*

class DoableRecipesListAdapter constructor(
    private val listener: onRecipeItemClickListener
): RecyclerView.Adapter<DoableRecipesListAdapter.ViewHolder>() {

    private val recipes = mutableListOf<RecipeWithUserIngredients>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doable_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun addRecipes(doableRecipes: List<RecipeWithUserIngredients>) {
        this.recipes.addAll(doableRecipes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(recipe: RecipeWithUserIngredients) {
            title.text = recipe.recipe.title

            containerView.setOnClickListener {
                listener.onItemClick(recipe.recipe.id)
            }

        }
    }

    interface onRecipeItemClickListener {
        fun onItemClick(recipeId: Int)
    }
}