package com.example.miaminstantapp.view.recipe_book

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.recipe_book.viewmodels.RecipeBookToDoRecipesViewModel

class RecipeBookToDoRecipesFragment: BaseFragment<RecipeBookToDoRecipesViewModel, RecipeBookToDoRecipesViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book_to_do_recipes

    override fun onStateChanged(state: RecipeBookToDoRecipesViewModel.State) {
        when(state) {
            is RecipeBookToDoRecipesViewModel.State.Error -> TODO()
            is RecipeBookToDoRecipesViewModel.State.FetchRecipesSuccess -> showRecipes(state.recipes)
            RecipeBookToDoRecipesViewModel.State.Loading -> TODO()
        }
    }

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        viewModel.fetchRecipes()
    }

    private fun showRecipes(recipes: List<RecipeBookRecipeEntity>) {
        recipes.map {
            Log.i("Recipe name", it.name)
        }
    }
}