package com.example.miaminstantapp.view

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.view.items.ShortIngredientItem
import com.example.miaminstantapp.viewmodel.userfilters.AddAlergiesViewModel
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_add_alergies.*
import kotlinx.android.synthetic.main.fragment_user_filters.*

class AddAlergiesFragment: BaseFragment<AddAlergiesViewModel, AddAlergiesViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_add_alergies
    lateinit var ingredientsAdapter: GroupAdapter<ShortIngredientItem.ShortIngredientItemViewHolder>

    override fun onStateChanged(state: AddAlergiesViewModel.State) {
        when(state) {
            is AddAlergiesViewModel.State.IngredientsFetched -> showIngredients(state.ingredients)
            is AddAlergiesViewModel.State.ExcludeIngredientAdded -> showAddedIngredient(state.ingredient)
            is AddAlergiesViewModel.State.ExcludedIngredientsFetched -> state.ingredients.map { addExcludedIngredient(it) }
        }
    }

    private fun addExcludedIngredient(ingredient: ExcludedIngredientEntity) {
        val chip = Chip(context)
        chip.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = ingredient.name
            textSize = 16f
        }

        allergiesChipGroup.addView(chip)

    }

    private fun showAddedIngredient(ingredient: IngredientShortDto) {
        val chip = Chip(context)
        chip.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = ingredient.name
            textSize = 16f
        }

        allergiesChipGroup.addView(chip)

    }

    private fun showIngredients(ingredients: List<IngredientShortDto>) {
        ingredientsAdapter.clear()
        ingredientsAdapter.update(ingredients.map { ingredient -> ShortIngredientItem(ingredient) { viewModel.add(ingredient) } })
    }

    override fun initViews() {
        super.initViews()
        ingredientsAdapter = GroupAdapter()
        ingredientList.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.fetchAdded()

        search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }
}