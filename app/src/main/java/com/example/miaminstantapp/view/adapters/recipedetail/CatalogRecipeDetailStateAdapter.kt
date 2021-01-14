package com.example.miaminstantapp.view.adapters.recipedetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailContentFragment
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailIngredientsListFragment
import java.lang.IllegalStateException

class CatalogRecipeDetailStateAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CatalogRecipeDetailIngredientsListFragment()
            1 -> CatalogRecipeDetailContentFragment()
            else -> CatalogRecipeDetailContentFragment()
        }
    }

}