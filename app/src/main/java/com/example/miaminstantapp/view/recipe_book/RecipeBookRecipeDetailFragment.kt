package com.example.miaminstantapp.view.recipe_book

import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.recipe_book.viewmodels.RecipeBookRecipeDetailViewModel
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailIngredientsListFragment
import kotlinx.android.synthetic.main.item_recipe_book_recipe.*

class RecipeBookRecipeDetailFragment: BaseFragment<RecipeBookRecipeDetailViewModel, RecipeBookRecipeDetailViewModel.State>() {

    companion object {
        const val RECIPE_ID_KEY = "recipeBookRecipeDetailKey"
    }

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book_recipe_detail

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        arguments?.let {
            val recipeId = it.getInt(RecipeBookRecipeDetailFragment.RECIPE_ID_KEY)
            viewModel.fetchRecipe(recipeId)
        }
    }

    override fun onStateChanged(state: RecipeBookRecipeDetailViewModel.State) {
        when(state) {
            is RecipeBookRecipeDetailViewModel.State.FetchRecipeSuccess -> showRecipe(state.recipe)
        }
    }

    private fun showRecipe(recipe: RecipeBookRecipeEntity) {
        recipeCard.setRecipeBookRecipe(recipe)
    }
}