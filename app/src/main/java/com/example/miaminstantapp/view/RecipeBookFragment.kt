package com.example.miaminstantapp.view

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.view.adapters.RecipeBookRecipeAdapter
import com.example.miaminstantapp.viewmodel.IRecipeBookViewModel
import kotlinx.android.synthetic.main.fragment_recipe_book.*

class RecipeBookFragment: BaseFragment<IRecipeBookViewModel, IRecipeBookViewModel.State>() {

    private lateinit var recipesAdapter: RecipeBookRecipeAdapter

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book

    override fun onStateChanged(state: IRecipeBookViewModel.State) {
        when(state) {
            is IRecipeBookViewModel.State.FetchRecipeSuccess -> showRecipes(state.recipes)
        }
    }

    private fun showRecipes(recipes: List<RecipeBookRecipeEntity>) {
        if (recipes.isNotEmpty()) recipesAdapter.setRecipes(recipes)
        else recipeBookEmptyView.isVisible = true
    }

    override fun initViews() {
        super.initViews()

        initRecipeList()
        recipeBookEmptyView.isVisible = false
        viewModel.fetchRecipes()
    }

    private fun initRecipeList() {
        recipesAdapter = RecipeBookRecipeAdapter()
        recipesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }
    }
}