package com.example.miaminstantapp.view.recipes_catalog

import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class CatalogRecipeFiltersViewModel @Inject constructor(

): BaseViewModel<CatalogRecipeFiltersViewModel.State>() {

    sealed class State {
        object FiltersAppliedSuccess: State()
    }

}