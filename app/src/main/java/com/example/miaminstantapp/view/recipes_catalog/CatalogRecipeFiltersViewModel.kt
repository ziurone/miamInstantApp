package com.example.miaminstantapp.view.recipes_catalog

import com.example.miaminstantapp.domain.actions.DietAction
import com.example.miaminstantapp.domain.actions.recipes_catalog.FetchRecipeTimeFilterAmountAction
import com.example.miaminstantapp.domain.actions.recipes_catalog.SetTotalTimeCatalogRecipeFilterAction
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class CatalogRecipeFiltersViewModel @Inject constructor(
    private val fetchRecipeTimeFilterAmountAction: FetchRecipeTimeFilterAmountAction,
    private val setTotalTimeCatalogRecipeFilterAction: SetTotalTimeCatalogRecipeFilterAction,
    private val dietAction: DietAction
): BaseViewModel<CatalogRecipeFiltersViewModel.State>() {

    sealed class State {
        object FiltersAppliedSuccess: State()
        data class FetchTimeAmountsSuccess(val amounts: List<Int>): State()
        data class FetchDietsSuccess(val diets: Array<Diet>): State()
    }

    init {
        listenSource(fetchRecipeTimeFilterAmountAction.getLiveData(), ::onFetchTimeAmounts)
        listenSource(setTotalTimeCatalogRecipeFilterAction.getLiveData(), ::onSetTotalTime)
        listenSource(dietAction.getLiveData(), {})
    }

    fun applyFilters(totalMinutes: Int?, diets: List<Diet>) {
        totalMinutes?.let {
            if(totalMinutes != 0) {
                setTotalTimeFilter(it)
            }
        }

        if(diets.isNotEmpty()) dietAction.addDiets(diets)

        setState(State.FiltersAppliedSuccess)
    }

    fun fetchDiets() {
        setState(State.FetchDietsSuccess(Diet.values()))
    }

    fun fetchRecipeTimeAmounts() {
        fetchRecipeTimeFilterAmountAction.fetchAmounts()
    }

    private fun setTotalTimeFilter(totalMinutes: Int) {
        setTotalTimeCatalogRecipeFilterAction(totalMinutes)
    }

    private fun onFetchTimeAmounts(result: FetchRecipeTimeFilterAmountAction.Result) {
        when(result) {
            is FetchRecipeTimeFilterAmountAction.Result.FetchSuccess -> setState(State.FetchTimeAmountsSuccess(result.amounts))
        }
    }

    private fun onSetTotalTime(result: SetTotalTimeCatalogRecipeFilterAction.Result) {
    }

}