package com.example.miaminstantapp.view.recipedetail.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailContentFragment
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailIngredientsListFragment
import com.example.miaminstantapp.view.recipedetail.RecipeBookRecipeIngredientsFragment

class RecipeBookRecipeDetailStateAdapter(
    fa: FragmentActivity,
    private val recipeId: Int
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                val bundle = bundleOf(RecipeBookRecipeIngredientsFragment.RECIPE_BOOK_INGREDIENTS_KEY to recipeId)
                val fragment = RecipeBookRecipeIngredientsFragment()
                fragment.arguments = bundle
                return fragment
            }
            1 -> CatalogRecipeDetailContentFragment()
            else -> throw IllegalArgumentException("Position in pager don't exist")
        }
    }

}