package com.example.miaminstantapp.view

import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import com.example.miaminstantapp.extensions.setFullScreen
import com.example.miaminstantapp.routing.Launcher
import com.example.miaminstantapp.view.items.RecipeItem
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailActivity.Companion.CATALOG_RECIPE_ID_KEY
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailFragment
import com.example.miaminstantapp.viewmodel.ICatalogRecipesListViewModel
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_catalog_recipes_list.*
import kotlinx.android.synthetic.main.toolbar.*

class CatalogRecipesListFragment: BaseFragment<ICatalogRecipesListViewModel, ICatalogRecipesListViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipes_list

    private val recipeAdapter = GroupAdapter<RecipeItem.RecipeItemViewHolder>()

    override fun initViews() {
        super.initViews()

        ingredientsAutocompleteInput.clearFocus()

        toolbarClose.title = "Buscar"

        ingredientsAutocompleteInput.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if(hasFocus) {
                    findNavController().navigate(R.id.action_global_toIngredientAutocomplete)
                    ingredientsAutocompleteInput.clearFocus()
                }
            }

        initRecipeList()
        viewModel.fetchRecipes()
    }

    private fun initRecipeList() {
        recipeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter
        }
    }

    override fun onStateChanged(state: ICatalogRecipesListViewModel.State) {

        when(state) {
            is ICatalogRecipesListViewModel.State.FetchedRecipesSuccess -> showRecipes(state.catalogRecipeRelations)
            is ICatalogRecipesListViewModel.State.Error -> Unit
        }
    }

    private fun showRecipes(catalogRecipeRelations: List<CatalogRecipeRelations>) {
        if (catalogRecipeRelations.isNotEmpty()) {
            val items = catalogRecipeRelations.map { doableRecipe ->
                RecipeItem(doableRecipe) {
                    Launcher(requireContext()).catalogRecipeDetail(doableRecipe.recipe.id)
                }
            }

            recipeAdapter.update(items)
        } else  { recipeListEmptyView.isVisible = true }
    }

}