package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IGetDoableRecipeByIdAction
import javax.inject.Inject

class DoableRecipeDetailViewModel @Inject constructor(
    private val getDoableRecipeByIdAction: IGetDoableRecipeByIdAction
): IDoableRecipeDetailViewModel() {

    init {
        listenSource(getDoableRecipeByIdAction.getLiveData(), ::onGetDoableRecipeByIdResult)
    }

    override fun fetchRecipe(recipeId: Int) {
        getDoableRecipeByIdAction.fetchRecipeById(recipeId)
    }

    private fun onGetDoableRecipeByIdResult(result: IGetDoableRecipeByIdAction.Result) {
        when(result) {
            is IGetDoableRecipeByIdAction.Result.Success -> setState(State.FetchRecipeSuccess(result.recipe))
        }
    }

}