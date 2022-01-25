package com.example.miaminstantapp.view.recipedetail

import android.util.Log
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.adapters.recipe_book.RecipeBookStateAdapter
import com.example.miaminstantapp.view.items.RecipeBookRecipeIngredientItem
import com.example.miaminstantapp.view.recipe_book.RecipeBookRecipeDetailFragment
import com.example.miaminstantapp.viewmodel.recipebook.RecipeBookRecipeIngredientsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_recipe_book.*
import kotlinx.android.synthetic.main.fragment_recipe_book_recipe_ingredients.*

class RecipeBookRecipeIngredientsFragment: BaseFragment<RecipeBookRecipeIngredientsViewModel, RecipeBookRecipeIngredientsViewModel.State>() {

    private val ingredientsAdapter = GroupAdapter<RecipeBookRecipeIngredientItem.RecipeBookRecipeIngredientItemViewHolder>()

    companion object {
        const val RECIPE_BOOK_INGREDIENTS_KEY = "recipeBookRecipeIngredientsKey"
    }

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book_recipe_ingredients

    override fun onStateChanged(state: RecipeBookRecipeIngredientsViewModel.State) {
        when(state) {
            is RecipeBookRecipeIngredientsViewModel.State.FetchIngredientsSuccess -> showIngredients(state.ingredients)
        }
    }

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        arguments?.let {
            val recipeId = it.getInt(RecipeBookRecipeIngredientsFragment.RECIPE_BOOK_INGREDIENTS_KEY)
            viewModel.fetchIngredients(recipeId)
        }
    }


    override fun initViews() {
        super.initViews()

        ingredientsList.adapter = ingredientsAdapter

    }

    private fun showIngredients(ingredients: List<RecipeBookRecipeIngredientEntity>) {
        val items = ingredients.map {
            RecipeBookRecipeIngredientItem(it)
        }
        ingredientsAdapter.update(items)
    }

}