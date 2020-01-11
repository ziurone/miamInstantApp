package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IAddRecipeAction
import com.example.miaminstantapp.domain.actions.IGetDoableRecipeByIdAction
import com.example.miaminstantapp.domain.entities.DoableRecipe
import javax.inject.Inject

class DoableRecipeDetailViewModel @Inject constructor(
    private val getDoableRecipeByIdAction: IGetDoableRecipeByIdAction,
    private val addRecipeAction: IAddRecipeAction
): IDoableRecipeDetailViewModel() {

    init {
        listenSource(getDoableRecipeByIdAction.getLiveData(), ::onGetDoableRecipeByIdResult)
        listenSource(addRecipeAction.getLiveData(), ::onAddDoableRecipe)
    }

    override fun fetchRecipe(recipeId: Int) {
        getDoableRecipeByIdAction.fetchRecipeById(recipeId)
    }

    override fun addRecipe(doableRecipe: DoableRecipe) {
        addRecipeAction.addRecipe(doableRecipe)
    }

    private fun onAddDoableRecipe(result: IAddRecipeAction.Result) {
        when(result) {
            is IAddRecipeAction.Result.Success -> setState(State.AddRecipeSuccess)
        }
    }



    private fun onGetDoableRecipeByIdResult(result: IGetDoableRecipeByIdAction.Result) {
        when(result) {
            is IGetDoableRecipeByIdAction.Result.Success -> setState(State.FetchRecipeSuccess(result.recipe))
        }
    }

}