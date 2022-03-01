package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientEntity

data class RecipeSearchCriteria(
    val ingredients: List<UserIngredientEntity>,
    val userMoney: Int,
    val branchesIds: List<BranchEntity>,
    val excludedIngredients: List<ExcludedIngredientEntity>,
    val excludedRecipesIds: List<Int>
)