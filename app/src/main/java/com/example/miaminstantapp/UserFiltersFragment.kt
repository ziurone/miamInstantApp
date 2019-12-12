package com.example.miaminstantapp

import android.util.Log
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.usecases.IFetchSuggestedIngredientsUseCase
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.viewmodel.IUserIngredientsViewModel

class UserFiltersFragment : BaseFragment<IUserIngredientsViewModel, IUserIngredientsViewModel.State>() {

    override fun initViews() {
        viewModel.fetchSuggestedIngredients()
        super.initViews()
    }

    override fun onStateChanged(state: IUserIngredientsViewModel.State) {
        when(state) {
            is IUserIngredientsViewModel.State.FetchSuggestedIngredientsSuccess -> logIngredients(state.ingredients)
        }
    }

    fun logIngredients(ingredients: List<Ingredient>) {
        Log.i("Trajo ingredientes", ingredients.first().name)
    }

    override fun getLayoutId(): Int = R.layout.fragment_user_filters
}
