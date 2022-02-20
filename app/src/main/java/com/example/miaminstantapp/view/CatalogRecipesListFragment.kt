package com.example.miaminstantapp.view

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.routing.Launcher
import com.example.miaminstantapp.view.items.RecipeItem
import com.example.miaminstantapp.viewmodel.ICatalogRecipesListViewModel
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_catalog_recipes_list.*
import kotlinx.android.synthetic.main.fragment_catalog_recipes_list.chipsGroupSuggestedIngredients
import kotlinx.android.synthetic.main.fragment_catalog_recipes_list.ingredientsAutocompleteInput

class CatalogRecipesListFragment: BaseFragment<ICatalogRecipesListViewModel, ICatalogRecipesListViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipes_list

    private val recipeAdapter = GroupAdapter<RecipeItem.RecipeItemViewHolder>()

    override fun initViews() {
        super.initViews()

        ingredientsAutocompleteInput.clearFocus()

        toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.filters -> {
                    findNavController().navigate(R.id.action_global_toRecipeCatalogFilters)
                    true
                }
                else -> false
            }

        }


        ingredientsAutocompleteInput.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus) {
                    findNavController().navigate(R.id.action_global_toIngredientAutocomplete)
                    ingredientsAutocompleteInput.clearFocus()
                }
            }

        initRecipeList()
        viewModel.searchRecipes()
        viewModel.fetchSuggestedIngredients()
    }

    private fun initRecipeList() {
        recipeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter
        }
    }

    override fun onStateChanged(state: ICatalogRecipesListViewModel.State) {

        when(state) {
            is ICatalogRecipesListViewModel.State.FetchedRecipesSuccess -> showRecipes(state.catalogRecipesAgreggates)
            is ICatalogRecipesListViewModel.State.Error -> Unit
            is ICatalogRecipesListViewModel.State.FetchSuggestedIngredientsSuccess -> showSuggestedIngredients(state.suggestedIngredients)
        }
    }

    private fun showSuggestedIngredients(suggestedIngredients: List<Ingredient>) {
        chipsGroupSuggestedIngredients.removeAllViews()
        suggestedIngredients.forEach { ingredient ->
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                text = ingredient.name
                isCloseIconVisible = true
                textSize = 16f

                setOnCloseIconClickListener {
                    it.visibility = View.GONE
                    viewModel.removeSuggestedIngredient(ingredient)
                }
            }

            chip.setOnClickListener {
                viewModel.addIngredient(ingredient)
                viewModel.removeSuggestedIngredient(ingredient)
                chipsGroupSuggestedIngredients.removeView(chip)
            }

            chipsGroupSuggestedIngredients.addView(chip)
        }
    }

    private fun showRecipes(catalogRecipeAggregates: List<CatalogRecipeAgreggate>) {
        if (catalogRecipeAggregates.isNotEmpty()) {
            val items = catalogRecipeAggregates.map { catalogRecipeAggregate ->
                RecipeItem(catalogRecipeAggregate) {
                    Launcher(requireContext()).catalogRecipeDetail(catalogRecipeAggregate.recipe.id)
                }
            }

            recipeAdapter.update(items)
        } else  { recipeListEmptyView.isVisible = false }
    }

}