package com.example.miaminstantapp.view

import android.util.Log
import com.example.miaminstantapp.R
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.viewmodel.userfilters.AddAlergiesViewModel

class AddAlergiesFragment: BaseFragment<AddAlergiesViewModel, AddAlergiesViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_add_alergies

    override fun onStateChanged(state: AddAlergiesViewModel.State) {
        when(state) {
            is AddAlergiesViewModel.State.IngredientsFetched -> showIngredients(state.ingredients)
        }
    }

    private fun showIngredients(ingredients: List<IngredientShortDto>) {
        ingredients.map {
            Log.i("Ingredient", it.name)
        }
    }

    override fun initViews() {
        super.initViews()
        viewModel.search("palta")
    }
}