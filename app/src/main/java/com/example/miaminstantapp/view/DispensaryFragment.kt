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

class DispensaryFragment : BaseFragment<IDispensaryViewModel, IDispensaryViewModel.State>(), AutocompleteUserIngredientsAdapter.OnAddSearchedIngredient {

    private lateinit var autocompleteIngredientAdapter: AutocompleteUserIngredientsAdapter
    private lateinit var selectedIngredientsAdapter: GroupAdapter<UserIngredientItem.UserIngredientItemViewHolder>
    private var ingredientsAdded = 0

    companion object {
        const val AUTOCOMPLETE_REQUEST_CODE = 1
    }

    override fun getLayoutId(): Int = R.layout.fragment_dispensary

    override fun initViews() {
        viewModel.loadVolumeUnits()
        viewModel.fetchUserIngredients()

        initializeAutocomplete()

        toolbarClose.title = getString(R.string.dispensary)
        toolbarClose.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
        toolbarClose.setNavigationOnClickListener { activity?.onBackPressed() }

        searchRecipesButton.setOnClickListener {
            fetchSearchCriteria()
        }

        selectedIngredientsAdapter = GroupAdapter()
        userIngredientsList.apply {
            adapter = selectedIngredientsAdapter
        }

        super.initViews()
    }

    // Region autocomplete
    private fun initializeAutocomplete() {
        ingredientsAutocompleteInput.afterDelayedTextChanged(::searchIngredientsByName)

        autocompleteIngredientAdapter = AutocompleteUserIngredientsAdapter(this)
        ingredientsAutocompleteList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = autocompleteIngredientAdapter
        }
    }

    private fun updateIngredientsAutocomplete(ingredients: List<Ingredient>) {
        autocompleteIngredientAdapter.setData(ingredients)
    }

    private fun fetchSearchCriteria() {
        viewModel.fetchSearchRecipeCriteria()
    }

    private fun onFetchSearchRecipeCriteriaSuccess(criteria: RecipeSearchCriteria) {
        viewModel.searchRecipes(criteria)
    }

    override fun onStateChanged(state: IDispensaryViewModel.State) {
        when(state) {
            is IDispensaryViewModel.State.Loading -> showLoading()
            is IDispensaryViewModel.State.FetchSuggestedIngredientsSuccess -> showSuggestedIngredients(state.ingredients)
            is IDispensaryViewModel.State.AddVolumeUnitsSuccess -> logVolumeUnit()
            is IDispensaryViewModel.State.Error -> showError(state.error)
            is IDispensaryViewModel.State.UserIngredientsUpdated -> updateSelectedIngredients(state.ingredients)
            is IDispensaryViewModel.State.SearchIngredientsByNameSuccess -> updateIngredientsAutocomplete(state.ingredients)
            is IDispensaryViewModel.State.SaveRecipesSuccess -> navigateToRecipeList()
            is IDispensaryViewModel.State.FetchSearchRecipeCriteriaSuccess -> onFetchSearchRecipeCriteriaSuccess(state.criteria)
        }
    }

    private fun navigateToRecipeList() {
        findNavController().navigate(R.id.action_global_market_recipes)
    }

    private fun searchIngredientsByName(ingredientName: CharSequence) {
        viewModel.searchIngredientByName(ingredientName.toString())
    }

    fun showLoading() {
        Log.i("Estoy re loading", "DOWNloading")
    }

    private fun showSuggestedIngredients(ingredients: List<Ingredient>) {
        chipsGroupSuggestedIngredients.removeAllViews()
        addSuggestedIngredients(ingredients)
    }

    private fun addSuggestedIngredients(ingredients: List<Ingredient>) {

        if(ingredients.isEmpty()) {
            suggestedIngredientsCardTitle.isVisible = false
        }

        ingredients.forEach {
            val ingredient = it
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                text = it.name
                isCloseIconVisible = true
                closeIcon = ContextCompat.getDrawable(context, R.drawable.ic_item_close)
                textSize = 16f
                setBackgroundColor(ContextCompat.getColor(context, R.color.secondary))
                setOnCloseIconClickListener {
                    visibility = View.GONE
                    viewModel.fetchSuggestedIngredients(ingredient.id)
                }
            }

            chip.setOnClickListener {
                onAddIngredient(ingredient)
                viewModel.fetchSuggestedIngredients(ingredient.id)
                chipsGroupSuggestedIngredients.removeView(chip)
            }

            chipsGroupSuggestedIngredients.addView(chip)
        }
    }

    private fun updateSelectedIngredients(ingredients: List<UserIngredientWithVolumeUnits>) {
        ingredientsAdded = ingredients.size

        if(ingredientsAdded == 0) {
            emptyView.isVisible = true
            selectedIngredientsCard.isVisible = false
        } else {
            selectedIngredientsCard.isVisible = true
            emptyView.isVisible = false
        }

        val ingredientsItems = ingredients.map{
            UserIngredientItem(it, requireContext(),{
                selectedIngredientsAdapter.clear()
                viewModel.removeUserIngredient(it.ingredient)
                viewModel.fetchUserIngredients()
            }, {
                ingredientId -> openEditIngredientScreen(ingredientId)
            })
        }

        selectedIngredientsAdapter.update(ingredientsItems)

        viewModel.fetchSuggestedIngredients()
    }

    private fun openEditIngredientScreen(ingredientId: Int) {
        findNavController().navigate(DispensaryFragmentDirections.fromDispensaryToEditIngredientFragment(ingredientId))
    }

    private fun logVolumeUnit() {
        Log.i("Inserto unidades", "bla")
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private fun onAddIngredient(ingredient: Ingredient) {
        viewModel.addIngredient(ingredient)
    }

    override fun onItemClick(ingredient: Ingredient) {
        onAddIngredient(ingredient)
        autocompleteIngredientAdapter.setData(listOf())
    }

}
