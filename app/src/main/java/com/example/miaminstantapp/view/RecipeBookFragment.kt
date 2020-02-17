package com.example.miaminstantapp.view

import android.util.Log
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
        recipesAdapter.setRecipes(recipes)
    }

    override fun initViews() {
        super.initViews()

        initRecipeList()
        viewModel.fetchRecipes()
    }

    fun initRecipeList() {
        recipesAdapter = RecipeBookRecipeAdapter()
        recipesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }
    }
}