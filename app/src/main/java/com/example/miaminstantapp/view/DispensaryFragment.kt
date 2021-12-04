package com.example.miaminstantapp.view

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.example.miaminstantapp.extensions.afterDelayedTextChanged
import com.example.miaminstantapp.view.adapters.AutocompleteUserIngredientsAdapter
import com.example.miaminstantapp.view.items.UserIngredientItem
import com.example.miaminstantapp.viewmodel.IDispensaryViewModel
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_dispensary.*
import kotlinx.android.synthetic.main.toolbar.*

class DispensaryFragment : BaseFragment<IDispensaryViewModel, IDispensaryViewModel.State>() {

    private lateinit var selectedIngredientsAdapter: GroupAdapter<UserIngredientItem.UserIngredientItemViewHolder>

    companion object {
        const val AUTOCOMPLETE_REQUEST_CODE = 5
    }

    override fun getLayoutId(): Int = R.layout.fragment_dispensary

    override fun initViews() {
        viewModel.fetchUserIngredients()

        toolbarClose.title = getString(R.string.dispensary)

        selectedIngredientsAdapter = GroupAdapter()
        userIngredientsList.apply {
            adapter = selectedIngredientsAdapter
        }

        super.initViews()
    }

    override fun onStateChanged(state: IDispensaryViewModel.State) {
        when(state) {
            is IDispensaryViewModel.State.Loading -> showLoading()
            is IDispensaryViewModel.State.Error -> showError(state.error)
            is IDispensaryViewModel.State.UserIngredientsUpdated -> updateSelectedIngredients(state.ingredients)
        }
    }

    fun showLoading() {
        Log.i("LOADING", "loading")
    }

    private fun updateSelectedIngredients(ingredients: List<UserIngredientWithVolumeUnits>) {

        if(ingredients.isEmpty()) {
            emptyView.isVisible = true
            selectedIngredientsCard.isVisible = false
        } else {
            selectedIngredientsCard.isVisible = true
            emptyView.isVisible = false
        }

        val ingredientsItems = ingredients.map{
            UserIngredientItem(it, requireContext()) {
//                selectedIngredientsAdapter.clear()
                viewModel.removeUserIngredient(it.ingredient)
                viewModel.fetchUserIngredients()
            }
        }

        selectedIngredientsAdapter.update(ingredientsItems)
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}
