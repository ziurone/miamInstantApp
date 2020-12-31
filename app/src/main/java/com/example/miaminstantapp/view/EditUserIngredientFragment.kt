package com.example.miaminstantapp.view

import android.text.SpannableStringBuilder
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.example.miaminstantapp.viewmodel.EditUserIngredientViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.edit_user_ingredient_fragment.*

class EditUserIngredientFragment : BaseBottomSheetDialogFragment<EditUserIngredientViewModel, EditUserIngredientViewModel.State>() {

    private val screenArgs by navArgs<EditUserIngredientFragmentArgs>()

    override fun getLayoutId(): Int = R.layout.edit_user_ingredient_fragment

    override fun onStateChanged(state: EditUserIngredientViewModel.State) {
        when(state) {
            is EditUserIngredientViewModel.State.FetchIngredientSuccess -> showIngredientData(state.ingredient)
            is EditUserIngredientViewModel.State.IngredientEditSuccess -> navigateToDispensary()
        }
    }

    private fun navigateToDispensary() {
        findNavController().popBackStack()
    }

    private fun showIngredientData(ingredient: UserIngredientWithVolumeUnits) {
        ingredientName.text = ingredient.ingredient.name
        ingredient.volumeUnits.forEach { volumeUnit ->
            if(volumeUnit.volumeUnitId == ingredient.ingredient.volumeUnitId) {
                unitInput.text = SpannableStringBuilder(ingredient.ingredient.volumeUnitQuantity.toString())
            }

            val chip = Chip(context)
            chip.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                text = volumeUnit.name
                textSize = 16f
                isCheckable = true
                setBackgroundColor(ContextCompat.getColor(context, R.color.secondary))
                isChecked = volumeUnit.volumeUnitId == ingredient.ingredient.volumeUnitId
                id = volumeUnit.volumeUnitId
            }

            volumeUnitsChipGroup.addView(chip)
        }

    }

    override fun initViews() {
        viewModel.fetchIngredient(screenArgs.ingredientId)
        editButton.setOnClickListener {
            viewModel.editVolumeUnit(
                ingredientId = screenArgs.ingredientId,
                volumeUnitId = volumeUnitsChipGroup.checkedChipId,
                volumUnitQuantity = unitInput.text.toString().toInt()
            )
        }
        super.initViews()
    }

}
