package com.example.miaminstantapp.view

import android.util.Log
import com.example.miaminstantapp.R
import com.example.miaminstantapp.viewmodel.IDoableRecipeDetailViewModel

class DoableRecipeFragment: BaseFragment<IDoableRecipeDetailViewModel, IDoableRecipeDetailViewModel.State>() {

    companion object {
        const val RECIPE_ID_KEY = "RecipeId"
    }

    override fun getLayoutId(): Int = R.layout.fragment_doable_recipe

    override fun initViews() {
        super.initViews()

        arguments?.let {
            val recipeId = it.getInt(RECIPE_ID_KEY)
            Log.i("RECIPE__ID", recipeId.toString())
        }
    }

    override fun onStateChanged(state: IDoableRecipeDetailViewModel.State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}