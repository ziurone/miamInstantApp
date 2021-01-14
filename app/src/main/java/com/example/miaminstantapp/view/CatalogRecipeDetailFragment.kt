package com.example.miaminstantapp.view

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.DoableRecipe
import com.example.miaminstantapp.view.adapters.MarketRecipeMarketIngredientsAdapter
import com.example.miaminstantapp.view.adapters.MarketRecipeUserIngredientsAdapter
import com.example.miaminstantapp.view.adapters.recipedetail.CatalogRecipeDetailStateAdapter
import com.example.miaminstantapp.viewmodel.IDoableRecipeDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_catalog_recipe_detail.*

class CatalogRecipeDetailFragment: BaseFragment<IDoableRecipeDetailViewModel, IDoableRecipeDetailViewModel.State>() {

    companion object {
        const val RECIPE_ID_KEY = "RecipeId"
    }

    private lateinit var recipe: DoableRecipe
    private lateinit var userIngredientsAdapter: MarketRecipeUserIngredientsAdapter
    private lateinit var marketIngredientsAdapter: MarketRecipeMarketIngredientsAdapter

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_detail

    override fun initViews() {
        super.initViews()

        initUserIngredientsList()
        initMarketIngredientsList()

        addRecipe.setOnClickListener {
            viewModel.addRecipe(recipe)
        }

        val adapter = CatalogRecipeDetailStateAdapter(requireActivity())
        recipeContentPager.adapter = adapter
        TabLayoutMediator(recipeContentTabLayout, recipeContentPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Ingredientes"
                1 -> "Contenido"
                else -> "Contenido no esperado"
            }

        }.attach()

        arguments?.let {
            val recipeId = it.getInt(RECIPE_ID_KEY)
            viewModel.fetchRecipe(recipeId)
        }
    }

    private fun initUserIngredientsList() {
        userIngredientsAdapter = MarketRecipeUserIngredientsAdapter()
        userIngredientsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userIngredientsAdapter
        }
    }

    private fun initMarketIngredientsList() {
        marketIngredientsAdapter = MarketRecipeMarketIngredientsAdapter()
        marketIngredientsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = marketIngredientsAdapter
        }
    }

    override fun onStateChanged(state: IDoableRecipeDetailViewModel.State) {
        when(state) {
            is IDoableRecipeDetailViewModel.State.FetchRecipeSuccess -> showRecipe(state.doableRecipe)
            is IDoableRecipeDetailViewModel.State.AddRecipeSuccess -> {
                recipeAddessSuccess()
            }
            is IDoableRecipeDetailViewModel.State.Error -> Unit
        }
    }

    private fun recipeAddessSuccess() {
        findNavController().navigate(R.id.action_global_toShopPurchase)
    }

    private fun showRecipe(doableRecipe: DoableRecipe) {
        recipe = doableRecipe
        recipeCard.setRecipe(doableRecipe)
        if (doableRecipe.userIngredients.isNotEmpty()) userIngredientsAdapter.addIngredients(doableRecipe.userIngredients)
        if (doableRecipe.marketIngredients.isNotEmpty()) marketIngredientsAdapter.addIngredients(doableRecipe.marketIngredients)
    }


}