package com.example.miaminstantapp.view

import android.util.Log
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.DoableRecipe
import com.example.miaminstantapp.viewmodel.IDoableRecipeDetailViewModel
import kotlinx.android.synthetic.main.fragment_doable_recipe.*

class DoableRecipeFragment: BaseFragment<IDoableRecipeDetailViewModel, IDoableRecipeDetailViewModel.State>() {

    companion object {
        const val RECIPE_ID_KEY = "RecipeId"
    }

    private lateinit var recipe: DoableRecipe

    override fun getLayoutId(): Int = R.layout.fragment_doable_recipe

    override fun initViews() {
        super.initViews()

        addRecipe.setOnClickListener {
            viewModel.addRecipe(recipe)
        }

        arguments?.let {
            val recipeId = it.getInt(RECIPE_ID_KEY)
            viewModel.fetchRecipe(recipeId)
        }
    }

    override fun onStateChanged(state: IDoableRecipeDetailViewModel.State) {
        when(state) {
            is IDoableRecipeDetailViewModel.State.FetchRecipeSuccess -> showRecipe(state.doableRecipe)
            is IDoableRecipeDetailViewModel.State.AddRecipeSuccess -> {
                Log.i("RECIPED_ADD_SUCCESS", "SUCCESS")
            }
        }
    }

    private fun showRecipe(doableRecipe: DoableRecipe) {
        recipe = doableRecipe
    }


}