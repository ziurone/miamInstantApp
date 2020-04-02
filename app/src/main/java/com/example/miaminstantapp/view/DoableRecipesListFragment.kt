package com.example.miaminstantapp.view

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.DoableRecipe
import com.example.miaminstantapp.view.adapters.DoableRecipesListAdapter
import com.example.miaminstantapp.viewmodel.IDoableRecipesViewModel
import kotlinx.android.synthetic.main.fragment_market_recipes_list.*

class DoableRecipesListFragment: BaseFragment<IDoableRecipesViewModel, IDoableRecipesViewModel.State>(), DoableRecipesListAdapter.OnRecipeItemClickListener {

    private lateinit var doableRecipesAdapter: DoableRecipesListAdapter

    override fun getLayoutId(): Int = R.layout.fragment_market_recipes_list

    override fun initViews() {
        super.initViews()

        initRecipeList()
        viewModel.fetchRecipes()
    }

    private fun initRecipeList() {
        doableRecipesAdapter = DoableRecipesListAdapter(this)
        recipeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doableRecipesAdapter
        }
    }

    override fun onStateChanged(state: IDoableRecipesViewModel.State) {

        when(state) {
            is IDoableRecipesViewModel.State.FetchedRecipesSuccess -> showRecipes(state.doableRecipes)
        }
    }

    private fun showRecipes(doableRecipes: List<DoableRecipe>) {
        if (doableRecipes.isNotEmpty()) doableRecipesAdapter.addRecipes(doableRecipes)
        else recipeListEmptyView.isVisible = true
    }

    override fun onItemClick(recipeId: Int) {
        val bundle = bundleOf(
            DoableRecipeFragment.RECIPE_ID_KEY to recipeId
        )

        findNavController().navigate(R.id.action_doableRecipeList_to_doableRecipeDetail, bundle)
    }

}