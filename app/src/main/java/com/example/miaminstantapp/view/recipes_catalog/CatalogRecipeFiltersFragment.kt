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
import kotlinx.android.synthetic.main.item_recipe_book_recipe_legacy.*

class CatalogRecipeFiltersFragment: BaseFragment<CatalogRecipeFiltersViewModel, CatalogRecipeFiltersViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_catalog_recipe_filters

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()
        viewModel.fetchRecipeTimeAmounts()
        showDiets()
    }

    override fun initViews() {
        super.initViews()

        applyFilters.setOnClickListener {
            val totalMinutes = if(timeAmountsGroup.checkedChipId != View.NO_ID) {
                 TimeChipsPresenter.getMinutesByChipText(timeAmountsGroup.findViewById<Chip>(timeAmountsGroup.checkedChipId).text as String)
            } else {
                null
            }

            val diets = dietsGroup.checkedChipIds.map {
                DietsPresenter.getDietFromLocalizedName(dietsGroup.findViewById<Chip>(it).text.toString())
            }

            viewModel.applyFilters(totalMinutes, diets)
        }

        clearFilters.setOnClickListener {
            dietsGroup.clearCheck()
            timeAmountsGroup.clearCheck()
        }
    }

    override fun onStateChanged(state: CatalogRecipeFiltersViewModel.State) {
        when(state) {
            is CatalogRecipeFiltersViewModel.State.FetchTimeAmountsSuccess -> showTimeFilters(state.amounts)
            CatalogRecipeFiltersViewModel.State.FiltersAppliedSuccess -> activity?.onBackPressed()
        }
    }

    private fun showDiets() {
        Diet.values().forEach { diet ->
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

                text = TimeChipsPresenter.getChipText(amount)
                isCloseIconVisible = false

            }
            timeAmountsGroup.addView(chip)
        }

    }
}