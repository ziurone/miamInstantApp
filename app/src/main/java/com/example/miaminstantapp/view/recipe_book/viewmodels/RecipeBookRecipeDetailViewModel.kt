package com.example.miaminstantapp.view.recipe_book.viewmodels

import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class RecipeBookRecipeDetailViewModel @Inject constructor(): BaseViewModel<RecipeBookRecipeDetailViewModel.State>() {
    sealed class State {

    }
}