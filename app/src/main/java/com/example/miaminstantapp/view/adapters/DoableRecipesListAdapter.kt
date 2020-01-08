package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.DoableRecipe
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_doable_recipe.*

class DoableRecipesListAdapter constructor(
    private val listener: OnRecipeItemClickListener
): RecyclerView.Adapter<DoableRecipesListAdapter.ViewHolder>() {

    private val recipes = mutableListOf<DoableRecipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doable_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun addRecipes(doableDoableRecipes: List<DoableRecipe>) {
        this.recipes.addAll(doableDoableRecipes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(doableRecipe: DoableRecipe) {
            title.text = doableRecipe.recipe.title

            containerView.setOnClickListener {
                listener.onItemClick(doableRecipe.recipe.id)
            }

        }
    }

    interface OnRecipeItemClickListener {
        fun onItemClick(recipeId: Int)
    }
}