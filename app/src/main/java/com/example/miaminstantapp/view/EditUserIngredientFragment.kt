package com.example.miaminstantapp.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.example.miaminstantapp.viewmodel.EditUserIngredientViewModel
import kotlinx.android.synthetic.main.edit_user_ingredient_fragment.*

class   EditUserIngredientFragment : BaseBottomSheetDialogFragment<EditUserIngredientViewModel, EditUserIngredientViewModel.State>() {

    private val screenArgs by navArgs<EditUserIngredientFragmentArgs>()

    override fun getLayoutId(): Int = R.layout.edit_user_ingredient_fragment

    override fun onStateChanged(state: EditUserIngredientViewModel.State) {
        when(state) {
            is EditUserIngredientViewModel.State.FetchIngredientSuccess -> showIngredientData(state.ingredient)
        }
    }

    private fun showIngredientData(ingredient: UserIngredientWithVolumeUnits) {
        title.text = ingredient.ingredient.name
    }

    override fun initViews() {
        viewModel.fetchIngredient(screenArgs.ingredientId)
        super.initViews()
    }

}
