package com.example.miaminstantapp.view.recipedetail

import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipe
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeMarketIngredientItem
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeMarketIngredientsHeaderItem
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeUserIngredientItem
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeUserIngredientsHeaderItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_catalog_recipe_detail_ingredients_list.*

class CatalogRecipeDetailIngredientsListFragment: BaseFragment<CatalogRecipeDetailIngredientsListViewModel, CatalogRecipeDetailIngredientsListViewModel.State>() {

    companion object {
        const val RECIPE_ID_KEY = "recipeIdKey"
    }

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_detail_ingredients_list

    override fun onStateChanged(state: CatalogRecipeDetailIngredientsListViewModel.State) {
        when(state) {
            is CatalogRecipeDetailIngredientsListViewModel.State.FetchSuccess -> showIngredients(state.recipe)
        }
    }

    override fun initViews() {
        super.initViews()
        arguments?.let {
            val recipeId = it.getInt(RECIPE_ID_KEY)
            viewModel.fetchRecipe(recipeId)
        }
        recipeIngredientsList.adapter = adapter
    }

    private fun showIngredients(catalogRecipe: CatalogRecipe) {
        val userIngredients = catalogRecipe.userIngredients
        val marketIngredientsItems = catalogRecipe.marketIngredients
        val items = mutableListOf<Group>()
        if(catalogRecipe.userIngredients.isNotEmpty()) {
            items.add(CatalogRecipeUserIngredientsHeaderItem())
        }

        items.addAll(userIngredients.map { CatalogRecipeUserIngredientItem(it) })

        if(catalogRecipe.marketIngredients.isNotEmpty()) {
            items.add(CatalogRecipeMarketIngredientsHeaderItem())
        }

        items.addAll(marketIngredientsItems.map { CatalogRecipeMarketIngredientItem(it) })

        adapter.update(items)
    }
}