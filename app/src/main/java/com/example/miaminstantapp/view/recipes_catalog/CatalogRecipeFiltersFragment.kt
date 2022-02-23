package com.example.miaminstantapp.view.recipes_catalog

import android.util.Log
import android.view.View
import android.view.View.NO_ID
import android.view.ViewGroup
import com.example.miaminstantapp.R
import com.example.miaminstantapp.SessionActivity
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.view.BaseFragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_catalog_recipe_filters.*

class CatalogRecipeFiltersFragment: BaseFragment<CatalogRecipeFiltersViewModel, CatalogRecipeFiltersViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_filters

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        viewModel.fetchRecipeTimeAmounts()
        viewModel.fetchDiets()
    }

    override fun initViews() {
        super.initViews()
        applyFilters.setOnClickListener {
            val totalMinutes = if(!timeAmountsGroup.findViewById<Chip>(timeAmountsGroup.checkedChipId).equals(View.NO_ID)) {
                 (timeAmountsGroup.findViewById<Chip>(timeAmountsGroup.checkedChipId).text as String).toInt()
            } else {
                null
            }

            val diets = dietsGroup.checkedChipIds.map {
                DietsPresenter.getDietFromLocalizedName(dietsGroup.findViewById<Chip>(it).text.toString())
            }

            viewModel.applyFilters(totalMinutes, diets)

        }
    }

    override fun onStateChanged(state: CatalogRecipeFiltersViewModel.State) {
        when(state) {
            is CatalogRecipeFiltersViewModel.State.FetchTimeAmountsSuccess -> showTimeFilters(state.amounts)
            CatalogRecipeFiltersViewModel.State.FiltersAppliedSuccess -> activity?.onBackPressed()
            is CatalogRecipeFiltersViewModel.State.FetchDietsSuccess -> showDiets(state.diets)
        }
    }

    private fun showDiets(diets: Array<Diet>) {
        diets.forEach { diet ->
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                text = DietsPresenter.getLocalizedName(diet)
                isCloseIconVisible = false
            }
            dietsGroup.addView(chip)
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

    }
}