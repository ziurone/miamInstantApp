package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recipe_book_recipe_legacy.*

class RecipeBookRecipeAdapter: RecyclerView.Adapter<RecipeBookRecipeAdapter.ViewHolder>() {

    private val recipes = mutableListOf<RecipeBookRecipeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_book_recipe_legacy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun setRecipes(recipesList: List<RecipeBookRecipeEntity>) {
        recipes.addAll(recipesList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(recipe: RecipeBookRecipeEntity) {
            recipeName.text = recipe.name
            totalMinutes.text = recipe.totalMinutes.toString()
        }
    }
}