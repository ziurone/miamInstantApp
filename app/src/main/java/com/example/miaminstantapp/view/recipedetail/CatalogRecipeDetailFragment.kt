package com.example.miaminstantapp.view.recipedetail

import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.adapters.recipedetail.CatalogRecipeDetailStateAdapter
import com.example.miaminstantapp.viewmodel.ICatalogRecipeDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_catalog_recipe_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class CatalogRecipeDetailFragment: BaseFragment<ICatalogRecipeDetailViewModel, ICatalogRecipeDetailViewModel.State>() {

    private lateinit var recipeAgreggate: CatalogRecipeAgreggate

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
            viewModel.addRecipe(recipeAgreggate)
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
            is ICatalogRecipeDetailViewModel.State.FetchRecipeSuccess -> showRecipe(state.catalogRecipeAgreggate)
            is ICatalogRecipeDetailViewModel.State.AddRecipeSuccess -> {
                addRecipeSuccess()
            }
            is ICatalogRecipeDetailViewModel.State.Error -> Unit
            ICatalogRecipeDetailViewModel.State.AddRecipeSuccess -> Unit
        }
    }

    private fun addRecipeSuccess() {
        findNavController().navigate(R.id.fromDetailToMarketIngredientsAdded)
    }

    private fun showRecipe(catalogRecipeAgreggate: CatalogRecipeAgreggate) {
        recipeCard.setRecipe(catalogRecipeAgreggate, true)
        recipeAgreggate = catalogRecipeAgreggate
    }


}