package com.example.miaminstantapp.view.recipedetail

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipe
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.adapters.recipedetail.CatalogRecipeDetailStateAdapter
import com.example.miaminstantapp.viewmodel.ICatalogRecipeDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_catalog_recipe_detail.*

class CatalogRecipeDetailFragment: BaseFragment<ICatalogRecipeDetailViewModel, ICatalogRecipeDetailViewModel.State>() {

    companion object {
        const val RECIPE_ID_KEY = "RecipeId"
    }

    private lateinit var recipe: CatalogRecipe

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_detail

    override fun initViews() {
        super.initViews()

        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomAppBar)
        bottomNav.isVisible = false

        addRecipe.setOnClickListener {
            viewModel.addRecipe(recipe)
        }

        arguments?.let {
            val recipeId = it.getInt(RECIPE_ID_KEY)
            viewModel.fetchRecipe(recipeId)

            val adapter = CatalogRecipeDetailStateAdapter(requireActivity(), recipeId)
            recipeContentPager.adapter = adapter
            TabLayoutMediator(recipeContentTabLayout, recipeContentPager) { tab, position ->
                tab.text = when(position) {
                    0 -> requireContext().getString(R.string.recipe_ingredients_tab_title)
                    1 -> requireContext().getString(R.string.recipe_preparation_tab_title)
                    else -> throw IllegalArgumentException("Tab title position error")
                }
            }.attach()
        }
    }

    override fun onStateChanged(state: ICatalogRecipeDetailViewModel.State) {
        when(state) {
            is ICatalogRecipeDetailViewModel.State.FetchRecipeSuccess -> showRecipe(state.catalogRecipe)
            is ICatalogRecipeDetailViewModel.State.AddRecipeSuccess -> {
                addRecipeSuccess()
            }
            is ICatalogRecipeDetailViewModel.State.Error -> Unit
            ICatalogRecipeDetailViewModel.State.AddRecipeSuccess -> Unit
            is ICatalogRecipeDetailViewModel.State.Error -> Unit
            is ICatalogRecipeDetailViewModel.State.FetchRecipeSuccess -> Unit
        }
    }

    private fun addRecipeSuccess() {
        findNavController().navigate(R.id.action_global_toShopPurchase)
    }

    private fun showRecipe(catalogRecipe: CatalogRecipe) {
        recipeCard.setRecipe(catalogRecipe)
        recipe = catalogRecipe
    }


}