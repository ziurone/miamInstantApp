package com.example.miaminstantapp.view.recipedetail

import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeMarketIngredientItem
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeMarketIngredientsHeaderItem
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeUserIngredientItem
import com.example.miaminstantapp.view.recipedetail.items.CatalogRecipeUserIngredientsHeaderItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_catalog_recipe_detail_ingredients_list.*


class CatalogRecipeDetailIngredientsListFragment: BaseFragment<CatalogRecipeDetailIngredientsListViewModel, CatalogRecipeDetailIngredientsListViewModel.State>() {

    companion object {
        const val RECIPE_ID_KEY = "recipeIdKey"
    }

    private val userIngredientsAdapter = GroupAdapter<GroupieViewHolder>()
    private val marketIngredientsAdapter = GroupAdapter<GroupieViewHolder>()

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_detail_ingredients_list

    override fun onStateChanged(state: CatalogRecipeDetailIngredientsListViewModel.State) {
        when(state) {
            is CatalogRecipeDetailIngredientsListViewModel.State.FetchSuccess -> showIngredients(state.recipe)
            CatalogRecipeDetailIngredientsListViewModel.State.RefetchSuccess -> TODO()
        }
    }

    override fun initViews() {
        super.initViews()

        arguments?.let {
            val recipeId = it.getInt(RECIPE_ID_KEY)
            viewModel.fetchRecipe(recipeId)
        }
        recipeIngredientsList.adapter = userIngredientsAdapter
        marketIngredientsList.adapter = marketIngredientsAdapter
    }

    private fun showIngredients(catalogRecipeRelations: CatalogRecipeRelations) {
        marketIngredientsAdapter.clear()
        userIngredientsAdapter.clear()

        val marketIngredientsItems = mutableListOf<Group>()
        if(catalogRecipeRelations.marketIngredients.isNotEmpty()) {
            marketIngredientsItems.add(CatalogRecipeMarketIngredientsHeaderItem())
        }
        marketIngredientsItems.addAll(catalogRecipeRelations.marketIngredients.map {
            CatalogRecipeMarketIngredientItem(it) { marketIngredientRelations ->
                viewModel.addUserIngredientFromMarketIngredient(marketIngredientRelations)
            }
        })

        val userIngredientsItems = mutableListOf<Group>()
        if(catalogRecipeRelations.userIngredients.isNotEmpty()) {
            userIngredientsItems.add(CatalogRecipeUserIngredientsHeaderItem())
        }
        userIngredientsItems.addAll(catalogRecipeRelations.userIngredients.map { CatalogRecipeUserIngredientItem(it) })


        marketIngredientsAdapter.update(marketIngredientsItems)
        userIngredientsAdapter.update(userIngredientsItems)
    }
}