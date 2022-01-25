package com.example.miaminstantapp.view.adapters.recipe_book

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.miaminstantapp.view.recipe_book.RecipeBookHistoryFragment
import com.example.miaminstantapp.view.recipe_book.RecipeBookToDoRecipesFragment

class RecipeBookStateAdapter(
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                val bundle = bundleOf(RecipeBookToDoRecipesFragment.RECIPE_BOOK_RECIPES_MADE_KEY to false)
                val fragment = RecipeBookToDoRecipesFragment()
                fragment.arguments = bundle
                fragment
            }
            1 -> {
                val bundle = bundleOf(RecipeBookToDoRecipesFragment.RECIPE_BOOK_RECIPES_MADE_KEY to true)
                val fragment = RecipeBookToDoRecipesFragment()
                fragment.arguments = bundle
                fragment
            }
            else -> throw IllegalArgumentException("Position in pager don't exist")
        }
    }

}