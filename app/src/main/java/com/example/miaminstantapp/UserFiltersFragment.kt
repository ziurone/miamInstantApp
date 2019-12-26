package com.example.miaminstantapp

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.extensions.afterDelayedTextChanged
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.viewmodel.IUserIngredientsViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_user_filters.*

class UserFiltersFragment : BaseFragment<IUserIngredientsViewModel, IUserIngredientsViewModel.State>(){

    override fun getLayoutId(): Int = R.layout.fragment_user_filters

    override fun initViews() {
        viewModel.loadMasterData()

        ingredientsAutocompleteInput.afterDelayedTextChanged(::searchIngredientsByName)

        super.initViews()
    }

    override fun onStateChanged(state: IUserIngredientsViewModel.State) {
        when(state) {
            is IUserIngredientsViewModel.State.Loading -> showLoading()
            is IUserIngredientsViewModel.State.FetchSuggestedIngredientsSuccess -> logIngredients(state.ingredients)
            is IUserIngredientsViewModel.State.AddVolumeUnitsSuccess -> logVolumeUnit()
            is IUserIngredientsViewModel.State.Error -> showError(state.error)
            is IUserIngredientsViewModel.State.UserIngredientsUpdated -> updateSelectedIngredients(state.ingredients)
        }
    }

    fun searchIngredientsByName(ingredientName: CharSequence) {

    }

    fun showLoading() {
        Log.i("Estoy re loading", "DOWNloading")
    }

    private fun logIngredients(ingredients: List<Ingredient>) {

        chipsGroupSuggestedIngredients.removeAllViews()
        ingredients.forEach{
            val ingredient = it
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                text = it.name
            }

            chip.setOnClickListener{
                onAddIngredient(ingredient)
                chipsGroupSuggestedIngredients.removeView(chip)
            }

            chipsGroupSuggestedIngredients.addView(chip)
        }
    }

    fun updateSelectedIngredients(ingredients: List<UserIngredientEntity>) {
        userIngredients.removeAllViews()
        ingredients.forEach{
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                text = it.name
            }

            userIngredients.addView(chip)
        }

    }

    fun logVolumeUnit() {
        Log.i("Inserto unidades", "bla")
    }

    fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private fun onAddIngredient(ingredient: Ingredient) {
        viewModel.addIngredient(ingredient)
    }
}
