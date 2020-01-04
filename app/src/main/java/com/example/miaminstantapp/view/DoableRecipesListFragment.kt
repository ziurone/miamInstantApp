package com.example.miaminstantapp.view

import android.util.Log
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeWithUserIngredients
import com.example.miaminstantapp.viewmodel.IDoableRecipesViewModel

class DoableRecipesListFragment: BaseFragment<IDoableRecipesViewModel, IDoableRecipesViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_doable_recipes_list

    override fun initViews() {
        super.initViews()

        viewModel.fetchRecipes()
    }

    override fun onStateChanged(state: IDoableRecipesViewModel.State) {

        when(state) {
            is IDoableRecipesViewModel.State.FetchedRecipesSuccess -> showRecipes(state.recipes)
        }
    }

    private fun showRecipes(recipes: List<RecipeWithUserIngredients>) {
        recipes.forEach {
            Log.i("RECIPES_FETCHED", it.recipe.title)
            Log.i("RECIPES_FETCHED", it.userIngredients.first().ingredientId.toString())
        }
    }
}