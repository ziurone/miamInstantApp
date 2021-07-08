package com.example.miaminstantapp.view.recipedetail

import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.adapters.recipedetail.CatalogRecipeDetailStateAdapter
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailActivity.Companion.CATALOG_RECIPE_ID_KEY
import com.example.miaminstantapp.viewmodel.ICatalogRecipeDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_catalog_recipe_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class CatalogRecipeDetailFragment: BaseFragment<ICatalogRecipeDetailViewModel, ICatalogRecipeDetailViewModel.State>() {

    private lateinit var recipe: CatalogRecipeRelations

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_detail

    private val screenArgs by navArgs<CatalogRecipeDetailFragmentArgs>()

    override fun initViews() {
        super.initViews()

        toolbarClose.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
        toolbarClose.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        addRecipe.setOnClickListener {
            viewModel.addRecipe(recipe)
        }

        val recipeId = screenArgs.catalogRecipeIdKey
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

    override fun onStateChanged(state: ICatalogRecipeDetailViewModel.State) {
        when(state) {
            is ICatalogRecipeDetailViewModel.State.FetchRecipeSuccess -> showRecipe(state.catalogRecipeRelations)
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

    private fun showRecipe(catalogRecipeRelations: CatalogRecipeRelations) {
        recipeCard.setRecipe(catalogRecipeRelations)
        recipe = catalogRecipeRelations
    }


}