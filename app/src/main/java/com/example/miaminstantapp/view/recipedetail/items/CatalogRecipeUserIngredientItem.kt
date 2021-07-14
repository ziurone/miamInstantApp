package com.example.miaminstantapp.view.recipedetail.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.relations.CatalogRecipeUserIngredientRelations
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CatalogRecipeUserIngredientItem(
    private val userIngredientEntity: CatalogRecipeUserIngredientRelations
): Item<CatalogRecipeUserIngredientItem.CatalogRecipeUserIngredientItemViewHolder>() {

    override fun getLayout(): Int = R.layout.item_catalog_recipe_user_ingredient

    override fun bind(viewHolder: CatalogRecipeUserIngredientItemViewHolder, position: Int) {
        viewHolder.name.text = userIngredientEntity.userIngredient.ingredientName
    }

    override fun createViewHolder(itemView: View): CatalogRecipeUserIngredientItemViewHolder = CatalogRecipeUserIngredientItemViewHolder(itemView)

    class CatalogRecipeUserIngredientItemViewHolder(view: View): GroupieViewHolder(view) {
        val name: TextView = view.findViewById(R.id.ingredientName);
    }
}