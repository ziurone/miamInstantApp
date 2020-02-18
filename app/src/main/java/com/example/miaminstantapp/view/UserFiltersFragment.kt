package com.example.miaminstantapp.view

import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.extensions.afterDelayedTextChanged
import com.example.miaminstantapp.view.adapters.AutocompleteUserIngredientsAdapter
import com.example.miaminstantapp.view.utils.ViewEnabler
import com.example.miaminstantapp.viewmodel.IUserIngredientsViewModel
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_user_filters.*

class UserFiltersFragment : BaseFragment<IUserIngredientsViewModel, IUserIngredientsViewModel.State>(), AutocompleteUserIngredientsAdapter.OnAddSearchedIngredient {

    private lateinit var autocompleteIngredientAdapter: AutocompleteUserIngredientsAdapter
    private lateinit var buttonEnabler: ViewEnabler
    private lateinit var branchesAddedCondition: ViewEnabler.Condition
    private lateinit var moneyOrIngredientAddedCondition: ViewEnabler.Condition
    private var branchesAdded = false
    private var ingredientsAdded = 0
    private var moneyAdded = false

    companion object {
        const val AUTOCOMPLETE_REQUEST_CODE = 1
    }

    override fun getLayoutId(): Int = R.layout.fragment_user_filters

    override fun initViews() {
        viewModel.loadMasterData()

        initializeAutocomplete()

        addAddress.setOnClickListener {
            navigateToAddressComponent()
        }

        userMoneyInput.afterDelayedTextChanged(::setUserMoney)

        searchRecipesButton.setOnClickListener {
            searchRecipes()
        }

        initSearchButtonEnabler()

        super.initViews()
    }

    private fun initSearchButtonEnabler() {
        searchRecipesButton.isEnabled = false
        buttonEnabler = ViewEnabler(searchRecipesButton)
        moneyOrIngredientAddedCondition = buttonEnabler.createCondition()
        branchesAddedCondition = buttonEnabler.createCondition()
        branchesAddedCondition.update(false)
        moneyOrIngredientAddedCondition.update(false)
        buttonEnabler.start()
    }

    private fun checkSearchButtonEnabledConditions() {
        branchesAddedCondition.update(branchesAdded)
        moneyOrIngredientAddedCondition.update(ingredientsAdded > 0 || moneyAdded)
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

    private fun searchRecipes() {
        viewModel.searchRecipes()
    }

    private fun setUserMoney(money: CharSequence) {
        viewModel.setUserMoney(money.toString().toInt())
    }

    override fun onStateChanged(state: IUserIngredientsViewModel.State) {
        when(state) {
            is IUserIngredientsViewModel.State.Loading -> showLoading()
            is IUserIngredientsViewModel.State.FetchSuggestedIngredientsSuccess -> showSuggestedIngredients(state.ingredients)
            is IUserIngredientsViewModel.State.AddVolumeUnitsSuccess -> logVolumeUnit()
            is IUserIngredientsViewModel.State.Error -> showError(state.error)
            is IUserIngredientsViewModel.State.UserIngredientsUpdated -> updateSelectedIngredients(state.ingredients)
            is IUserIngredientsViewModel.State.SearchIngredientsByNameSuccess -> updateIngredientsAutocomplete(state.ingredients)
            is IUserIngredientsViewModel.State.AddMoneySuccess -> setMoneySuccess()
            is IUserIngredientsViewModel.State.SaveRecipesSuccess -> navigateToRecipeList()
            is IUserIngredientsViewModel.State.FetchShopsSuccess -> branchesAddedSuccesfully()
        }
    }

    private fun branchesAddedSuccesfully() {
        branchesAdded = true
        checkSearchButtonEnabledConditions()
    }

    private fun navigateToRecipeList() {
        findNavController().navigate(R.id.action_userFilters_to_doableRecipes)
    }

    private fun setMoneySuccess() {
        moneyAdded = true
        checkSearchButtonEnabledConditions()
    }

    private fun searchIngredientsByName(ingredientName: CharSequence) {
        viewModel.searchIngredientByName(ingredientName.toString())
    }

    fun showLoading() {
        Log.i("Estoy re loading", "DOWNloading")
    }

    private fun showSuggestedIngredients(ingredients: List<Ingredient>) {

        chipsGroupSuggestedIngredients.removeAllViews()
        ingredients.forEach{
            val ingredient = it
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                text = it.name
                textSize = 16f
            }

            chip.setOnClickListener{
                onAddIngredient(ingredient)
                chipsGroupSuggestedIngredients.removeView(chip)
            }

            chipsGroupSuggestedIngredients.addView(chip)
        }
    }

    private fun updateSelectedIngredients(ingredients: List<UserIngredientEntity>) {
        ingredientsAdded = ingredients.size
        checkSearchButtonEnabledConditions()
        userIngredients.removeAllViews()
        ingredients.forEach{
            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                text = it.name
                textSize = 16f
            }

            userIngredients.addView(chip)
        }

    }

    fun logVolumeUnit() {
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

    private fun navigateToAddressComponent() {
        val autocompleteFields = listOf(
            Place.Field.ID,
            Place.Field.ADDRESS)

        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN,
            autocompleteFields
        ).build(context!!)


        startActivityForResult(intent,
            AUTOCOMPLETE_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            AUTOCOMPLETE_REQUEST_CODE -> showSelectedAddress(data)
        }
    }

    private fun showSelectedAddress(data: Intent?) {
        val place = Autocomplete.getPlaceFromIntent(data!!)
        userAddress.text = place.address
        noAddressMessage.isVisible = false
        viewModel.fetchZoneShops(place.latLng?.latitude.toString(), place.latLng?.longitude.toString(), 10)
    }
}
