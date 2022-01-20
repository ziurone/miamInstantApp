package com.example.miaminstantapp.view

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.view.adapters.RecipeBookRecipeAdapter
import com.example.miaminstantapp.view.adapters.recipe_book.RecipeBookStateAdapter
import com.example.miaminstantapp.viewmodel.IRecipeBookViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_catalog_recipe_detail.*
import kotlinx.android.synthetic.main.fragment_recipe_book.*

class RecipeBookFragment: BaseFragment<IRecipeBookViewModel, IRecipeBookViewModel.State>() {

    private lateinit var recipesAdapter: RecipeBookRecipeAdapter

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book

    override fun onStateChanged(state: IRecipeBookViewModel.State) {
    }

    private fun showRecipes(recipes: List<RecipeBookRecipeEntity>) {
    }

    override fun initViews() {
        super.initViews()

        val adapter = RecipeBookStateAdapter(requireActivity())
        recipeBookPager.adapter = adapter
        TabLayoutMediator(recipeBookContentTabLayout, recipeBookPager) { tab, position ->
            tab.text = when(position) {
                0 -> "A preparar"
                1 -> "Preparadas"
                else -> throw IllegalArgumentException("Tab title position error")
            }
        }.attach()

    }

    private fun initRecipeList() {
    }
}