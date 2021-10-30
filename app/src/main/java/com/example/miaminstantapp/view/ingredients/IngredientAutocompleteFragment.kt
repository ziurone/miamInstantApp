package com.example.miaminstantapp.view.ingredients

import com.example.miaminstantapp.R
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.ingredients.viewmodels.IngredientAutocompleteViewModel

class IngredientAutocompleteFragment: BaseFragment<IngredientAutocompleteViewModel, IngredientAutocompleteViewModel.State>() {
    override fun getLayoutId(): Int = R.layout.fragment_ingredient_autocomplete

    override fun onStateChanged(state: IngredientAutocompleteViewModel.State) {
        TODO("Not yet implemented")
    }
}