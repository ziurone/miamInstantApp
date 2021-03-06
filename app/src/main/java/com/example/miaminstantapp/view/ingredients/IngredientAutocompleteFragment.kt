package com.example.miaminstantapp.view.ingredients

import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.extensions.afterDelayedTextChanged
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.ingredients.viewmodels.IngredientAutocompleteViewModel
import com.example.miaminstantapp.view.items.AutocompleteIngredientItem
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_catalog_recipes_list.*
import kotlinx.android.synthetic.main.fragment_ingredient_autocomplete.*
import kotlinx.android.synthetic.main.fragment_ingredient_autocomplete.ingredientsAutocompleteInput
import kotlinx.android.synthetic.main.fragment_ingredient_autocomplete.searchIngredients
import kotlinx.android.synthetic.main.toolbar.*

class IngredientAutocompleteFragment: BaseFragment<IngredientAutocompleteViewModel, IngredientAutocompleteViewModel.State>() {

    private val ingredientAdapter = GroupAdapter<AutocompleteIngredientItem.AutocompleteIngredientItemViewHolder>()

    override fun getLayoutId(): Int = R.layout.fragment_ingredient_autocomplete

    override fun initViews() {
        super.initViews()
        toolbarClose.title = "Buscar"
        ingredientsAutocompleteInput.afterDelayedTextChanged(::searchIngredientsByName)
        ingredientsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ingredientAdapter
        }

        searchIngredients.setStartIconOnClickListener {
            findNavController().popBackStack()
        }

        ingredientsAutocompleteInput.requestFocus()
    }

    override fun onStateChanged(state: IngredientAutocompleteViewModel.State) {
        when(state) {
            is IngredientAutocompleteViewModel.State.IngredientsRetrieved -> showIngredients(state.ingredients)
            IngredientAutocompleteViewModel.State.Loading -> showLoading(true)
            IngredientAutocompleteViewModel.State.AddUserIngredientSuccess -> onAddUserIngredientSuccess()
        }
    }

    private fun showLoading(show: Boolean) {
        loading.isVisible = show
    }

    private fun onAddUserIngredientSuccess() {
        showLoading(false)
        activity?.onBackPressed()
    }

    private fun searchIngredientsByName(ingredientName: CharSequence) {
        viewModel.searchIngredientByName(ingredientName.toString())
    }

    private fun showIngredients(ingredients: List<Ingredient>) {
        showLoading(false)
        val items = ingredients.map { ing -> AutocompleteIngredientItem(ing) {
            viewModel.addUserIngredient(
                ing
            )
        }
        }
        ingredientAdapter.update(items)
    }
}