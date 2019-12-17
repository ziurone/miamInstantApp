package com.example.miaminstantapp

import android.util.Log
import android.widget.Toast
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.viewmodel.IUserIngredientsViewModel
import com.google.android.material.snackbar.Snackbar

class UserFiltersFragment : BaseFragment<IUserIngredientsViewModel, IUserIngredientsViewModel.State>() {

    override fun initViews() {
        viewModel.loadMasterData()
        super.initViews()
    }

    override fun onStateChanged(state: IUserIngredientsViewModel.State) {
        when(state) {
            is IUserIngredientsViewModel.State.Loading -> showLoading()
            is IUserIngredientsViewModel.State.FetchSuggestedIngredientsSuccess -> logIngredients(state.ingredients)
            is IUserIngredientsViewModel.State.FetchVolumeUnitsSuccess -> logVolumeUnit(state.volumeUnits)
            is IUserIngredientsViewModel.State.Error -> showError(state.error)
        }
    }

    fun showLoading() {
        Log.i("Estoy re loading", "DOWNloading")
    }

    fun logIngredients(ingredients: List<Ingredient>) {
        Log.i("Trajo ingredientes", ingredients.first().name)
    }

    fun logVolumeUnit(volumeUnits: List<VolumeUnitEntity>) {
        Log.i("Trajo unidades", volumeUnits.first().name)
    }

    fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun getLayoutId(): Int = R.layout.fragment_user_filters
}
