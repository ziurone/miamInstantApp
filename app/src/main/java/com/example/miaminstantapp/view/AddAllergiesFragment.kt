package com.example.miaminstantapp.view

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.extensions.hideKeyboard
import com.example.miaminstantapp.view.items.ShortIngredientItem
import com.example.miaminstantapp.viewmodel.userfilters.AddAlergiesViewModel
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_add_allergies.*
import kotlinx.android.synthetic.main.toolbar.*

class AddAllergiesFragment: BaseFragment<AddAlergiesViewModel, AddAlergiesViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_add_allergies
    lateinit var ingredientsAdapter: GroupAdapter<ShortIngredientItem.ShortIngredientItemViewHolder>

    override fun onStateChanged(state: AddAlergiesViewModel.State) {
        when(state) {
            is AddAlergiesViewModel.State.IngredientsFetched -> showIngredients(state.ingredients)
            is AddAlergiesViewModel.State.ExcludeIngredientAdded -> showAddedIngredient(state.ingredient)
            is AddAlergiesViewModel.State.ExcludedIngredientsFetched -> state.ingredients.map { addExcludedIngredient(it) }
            AddAlergiesViewModel.State.ExcludedIngredientRemove -> Log.i("NON_ACTION_EVENT", "Excluded ingredient remove")
        }
    }

    private fun addExcludedIngredient(ingredient: ExcludedIngredientEntity) {
        val chip = Chip(context)
        chip.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = ingredient.name
            isCloseIconVisible = true
            textSize = 16f
            setBackgroundColor(ContextCompat.getColor(context, R.color.secondary))
            setOnCloseIconClickListener{
                viewModel.remove(ingredient.ingredientId)
                visibility = View.GONE
            }
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
            isCloseIconVisible = true
            setBackgroundColor(ContextCompat.getColor(context, R.color.secondary))
            textSize = 16f
            setOnCloseIconClickListener{
                viewModel.remove(ingredient.id)
                visibility = View.GONE
            }
        }

        allergiesChipGroup.addView(chip)

    }

    private fun showIngredients(ingredients: List<IngredientShortDto>) {
        ingredientsAdapter.clear()
        ingredientsAdapter.update(ingredients.map { ingredient -> ShortIngredientItem(ingredient) {
            viewModel.add(ingredient)
            ingredientsAdapter.clear()
            hideKeyboard()
        } })
    }

    override fun initViews() {
        super.initViews()
        ingredientsAdapter = GroupAdapter()
        ingredientList.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        next.setOnClickListener {
            findNavController().navigate(R.id.from_addUserAlergies_to_AddUserAddress)
        }

        skip.setOnClickListener {
            findNavController().navigate(R.id.from_addUserAlergies_to_AddUserAddress)
        }

        toolbarClose.title = getString(R.string.step_2_of_3)
        toolbarClose.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
        toolbarClose.setNavigationOnClickListener { findNavController().popBackStack() }
        viewModel.fetchAdded()

        search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ingredientsAdapter.clear()
                if(s.toString().length > 3) {
                    viewModel.search(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }
}