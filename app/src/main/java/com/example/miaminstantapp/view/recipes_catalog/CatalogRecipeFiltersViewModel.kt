package com.example.miaminstantapp.view.recipes_catalog

import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.actions.recipes_catalog.FetchRecipeTimeFilterAmountAction
import com.example.miaminstantapp.domain.actions.recipes_catalog.SetTotalTimeCatalogRecipeFilterAction
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class CatalogRecipeFiltersViewModel @Inject constructor(
    private val fetchRecipeTimeFilterAmountAction: FetchRecipeTimeFilterAmountAction,
    private val setTotalTimeCatalogRecipeFilterAction: SetTotalTimeCatalogRecipeFilterAction,
    private val dietAction: DietAction,
    private val searchRecipesAction: SearchRecipesAction,
    private val fetchSearchRecipeCriteriaAction: FetchSearchRecipeCriteriaAction
): BaseViewModel<CatalogRecipeFiltersViewModel.State>() {

    sealed class State {
        object FiltersAppliedSuccess: State()
        data class FetchTimeAmountsSuccess(val amounts: List<Int>): State()
        object SearchRecipesSuccces: State()
    }

    init {
        listenSource(fetchRecipeTimeFilterAmountAction.getLiveData(), ::onFetchTimeAmounts)
        listenSource(setTotalTimeCatalogRecipeFilterAction.getLiveData(), ::onSetTotalTime)
        listenSource(dietAction.getLiveData(), ::onDietsResults)
        listenSource(fetchSearchRecipeCriteriaAction.getLiveData(), ::onFetchSearchRecipeCriteriaResult)
        listenSource(searchRecipesAction.getLiveData(), ::onSearchRecipesResult)
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

    fun refreshRecipes() {
        fetchSearchRecipeCriteriaAction.fetch()
    }

    private fun onSearchRecipesResult(result: ISearchRecipesAction.Result) {
        setState(State.SearchRecipesSuccces)
    }

    private fun searchRecipes(criteria: RecipeSearchCriteria) {
        searchRecipesAction.searchRecipes(criteria)
    }

    private fun onFetchSearchRecipeCriteriaResult(result: IFetchSearchRecipeCriteriaAction.Result) {
        when(result) {
            is IFetchSearchRecipeCriteriaAction.Result.Error -> TODO()
            is IFetchSearchRecipeCriteriaAction.Result.Success -> searchRecipes(result.recipeSearchCriteria)
        }
    }

    private fun onDietsResults(result: DietAction.Result) {

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