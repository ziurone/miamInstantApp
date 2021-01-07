package com.example.miaminstantapp.view

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.DoableRecipe
import com.example.miaminstantapp.view.items.RecipeItem
import com.example.miaminstantapp.viewmodel.ICatalogRecipesListViewModel
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_catalog_recipes_list.*

class CatalogRecipesListFragment: BaseFragment<ICatalogRecipesListViewModel, ICatalogRecipesListViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipes_list

    private val recipeAdapter = GroupAdapter<RecipeItem.RecipeItemViewHolder>()

    override fun initViews() {
        super.initViews()

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
            is ICatalogRecipesListViewModel.State.FetchedRecipesSuccess -> showRecipes(state.doableRecipes)
            is ICatalogRecipesListViewModel.State.Error -> Unit
        }
    }

    private fun showRecipes(doableRecipes: List<DoableRecipe>) {
        if (doableRecipes.isNotEmpty()) {
            val items = doableRecipes.map { doableRecipe ->
                RecipeItem(doableRecipe) {
                    val bundle = bundleOf(
                        CatalogRecipeDetailFragment.RECIPE_ID_KEY to doableRecipe.recipe.id
                    )
                    findNavController().navigate(R.id.action_doableRecipeList_to_doableRecipeDetail, bundle)
                }
            }

            recipeAdapter.update(items)
        } else  { recipeListEmptyView.isVisible = true }
    }

}