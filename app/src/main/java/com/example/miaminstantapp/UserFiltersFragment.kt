package com.example.miaminstantapp

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.adapters.IngredientsChipAdapter
import com.example.miaminstantapp.viewmodel.IUserIngredientsViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_user_filters.*

class UserFiltersFragment : BaseFragment<IUserIngredientsViewModel, IUserIngredientsViewModel.State>(), IngredientsChipAdapter.IngredientItemClickListener {

    private lateinit var suggestedIngredientsAdapter: IngredientsChipAdapter

    override fun getLayoutId(): Int = R.layout.fragment_user_filters

    override fun initViews() {
        viewModel.loadMasterData()

        suggestedIngredientsAdapter = IngredientsChipAdapter(this)

        suggestedIngredientsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = suggestedIngredientsAdapter
        }

        super.initViews()
    }

    override fun onStateChanged(state: IUserIngredientsViewModel.State) {
        when(state) {
            is IUserIngredientsViewModel.State.Loading -> showLoading()
            is IUserIngredientsViewModel.State.FetchSuggestedIngredientsSuccess -> logIngredients(state.ingredients)
            is IUserIngredientsViewModel.State.AddVolumeUnitsSuccess -> logVolumeUnit()
            is IUserIngredientsViewModel.State.Error -> showError(state.error)
        }
    }

    fun showLoading() {
        Log.i("Estoy re loading", "DOWNloading")
    }

    private fun logIngredients(ingredients: List<Ingredient>) {
        suggestedIngredientsAdapter.setData(ingredients)

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
                onClick(ingredient)
                chipsGroupSuggestedIngredients.removeView(chip)
            }

            chipsGroupSuggestedIngredients.addView(chip)
        }
    }

    fun logVolumeUnit() {
        Log.i("Inserto unidades", "bla")
    }

    fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onClick(ingredient: Ingredient) {
        viewModel.addIngredient(ingredient)
    }
}
