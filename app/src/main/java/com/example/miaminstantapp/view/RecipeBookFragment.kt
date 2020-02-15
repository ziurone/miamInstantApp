package com.example.miaminstantapp.view

import android.util.Log
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.viewmodel.IRecipeBookViewModel

class RecipeBookFragment: BaseFragment<IRecipeBookViewModel, IRecipeBookViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book

    override fun onStateChanged(state: IRecipeBookViewModel.State) {
        when(state) {
            is IRecipeBookViewModel.State.FetchRecipeSuccess -> showRecipes(state.recipes)
        }
    }

    private fun showRecipes(recipes: List<RecipeBookRecipeEntity>) {
        Log.i("RECIPE_ADDED", recipes.first().name)
    }

    override fun initViews() {
        super.initViews()

        viewModel.fetchRecipes()
    }
}