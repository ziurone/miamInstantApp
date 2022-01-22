package com.example.miaminstantapp.view.recipe_book

import com.example.miaminstantapp.R
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.recipe_book.viewmodels.RecipeBookRecipeDetailViewModel

class RecipeBookRecipeDetailFragment: BaseFragment<RecipeBookRecipeDetailViewModel, RecipeBookRecipeDetailViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book_recipe_detail

    override fun onStateChanged(state: RecipeBookRecipeDetailViewModel.State) {
        TODO("Not yet implemented")
    }
}