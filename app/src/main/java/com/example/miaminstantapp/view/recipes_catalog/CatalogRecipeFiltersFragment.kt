package com.example.miaminstantapp.view.recipes_catalog

import android.view.ViewGroup
import com.example.miaminstantapp.R
import com.example.miaminstantapp.SessionActivity
import com.example.miaminstantapp.view.BaseFragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_catalog_recipe_filters.*

class CatalogRecipeFiltersFragment: BaseFragment<CatalogRecipeFiltersViewModel, CatalogRecipeFiltersViewModel.State>() {

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        viewModel.fetchRecipeTimeAmounts()
    }

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_filters

    override fun onStateChanged(state: CatalogRecipeFiltersViewModel.State) {
        when(state) {
            is CatalogRecipeFiltersViewModel.State.FetchTimeAmountsSuccess -> showTimeFilters(state.amounts)
            CatalogRecipeFiltersViewModel.State.FiltersAppliedSuccess -> TODO()
        }
    }

    private fun showTimeFilters(amounts: List<Int>) {
        amounts.forEach { amount ->
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                text = amount.toString()
                isCloseIconVisible = false

            }
            timeAmountsGroup.addView(chip)
        }

        timeAmountsGroup.findViewById<Chip>(timeAmountsGroup.checkedChipId).text

    }
}