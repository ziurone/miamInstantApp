package com.example.miaminstantapp.view.recipe_book

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.view.BaseFragment
import com.example.miaminstantapp.view.items.RecipeBookRecipeItem
import com.example.miaminstantapp.view.recipe_book.viewmodels.RecipeBookToDoRecipesViewModel
import com.example.miaminstantapp.view.recipedetail.RecipeBookRecipeIngredientsFragment
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_recipe_book_to_do_recipes.*

class RecipeBookToDoRecipesFragment: BaseFragment<RecipeBookToDoRecipesViewModel, RecipeBookToDoRecipesViewModel.State>() {

    companion object {
        const val RECIPE_BOOK_RECIPES_MADE_KEY = "recipeBookRecipesMadeKey"
    }

    private val recipeAdapter = GroupAdapter<RecipeBookRecipeItem.RecipeBookRecipeItemViewHolder>()

    override fun getLayoutId(): Int = R.layout.fragment_recipe_book_to_do_recipes

    override fun onStateChanged(state: RecipeBookToDoRecipesViewModel.State) {
        when(state) {
            is RecipeBookToDoRecipesViewModel.State.Error -> TODO()
            is RecipeBookToDoRecipesViewModel.State.FetchRecipesSuccess -> showRecipes(state.recipes)
            RecipeBookToDoRecipesViewModel.State.Loading -> TODO()
        }
    }

    override fun initViews() {
        super.initViews()

        recipesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter
        }


    }

    override fun startInitialDomainAction() {
        super.startInitialDomainAction()

        arguments?.let {
            val made = it.getBoolean(RecipeBookToDoRecipesFragment.RECIPE_BOOK_RECIPES_MADE_KEY, false)
            viewModel.fetchRecipes(made)
        }
    }

    private fun showRecipes(recipes: List<RecipeBookRecipeEntity>) {
        recipeAdapter.update(
            recipes.map { RecipeBookRecipeItem(it, ::navigateToRecipeDetail)}
        )
    }

    private fun navigateToRecipeDetail(recipeId: Int) {
        val bundle = bundleOf(RecipeBookRecipeDetailFragment.RECIPE_ID_KEY to recipeId)
        findNavController().navigate(R.id.action_global_toRecipeBookRecipeDetail, bundle)
    }
}