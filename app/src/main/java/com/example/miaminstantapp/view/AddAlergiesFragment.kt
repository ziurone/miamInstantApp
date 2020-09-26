package com.example.miaminstantapp.view

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.view.items.ShortIngredientItem
import com.example.miaminstantapp.viewmodel.userfilters.AddAlergiesViewModel
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_add_alergies.*

class AddAlergiesFragment: BaseFragment<AddAlergiesViewModel, AddAlergiesViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_add_alergies
    lateinit var ingredientsAdapter: GroupAdapter<ShortIngredientItem.ShortIngredientItemViewHolder>

    override fun onStateChanged(state: AddAlergiesViewModel.State) {
        when(state) {
            is AddAlergiesViewModel.State.IngredientsFetched -> showIngredients(state.ingredients)
        }
    }

    private fun showIngredients(ingredients: List<IngredientShortDto>) {
        ingredientsAdapter.clear()
        ingredientsAdapter.update(ingredients.map { ShortIngredientItem(it) })
    }

    override fun initViews() {
        super.initViews()
        ingredientsAdapter = GroupAdapter()
        ingredientList.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }
}