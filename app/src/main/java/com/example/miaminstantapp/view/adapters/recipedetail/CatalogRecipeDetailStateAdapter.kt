package com.example.miaminstantapp.view.adapters.recipedetail

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailContentFragment
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailIngredientsListFragment
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailIngredientsListFragment.Companion.RECIPE_ID_KEY
import java.lang.IllegalStateException

class CatalogRecipeDetailStateAdapter(
    fa: FragmentActivity,
    private val recipeId: Int
): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                val bundle = bundleOf(RECIPE_ID_KEY to recipeId)
                val fragment = CatalogRecipeDetailIngredientsListFragment()
                fragment.arguments = bundle
                return fragment
            }
            1 -> CatalogRecipeDetailContentFragment()
            else -> throw IllegalArgumentException("Position in pager don't exist")
        }
    }

}