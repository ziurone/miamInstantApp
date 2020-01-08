package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import com.example.miaminstantapp.domain.entities.RecipeUserIngredientEntity

data class MarketRecipeDTO(
    val id: Int,

    val title: String,

    val content: String,

    val link: String,

    val servings: Int,

    val price: Float,

    val preparingMinutes: Int,

    val cookingMinutes: Int,

    val totalMinutes: Int,

    val siteId: Int,

    val imageUrl: String,

    val marketIngredients: List<MarketIngredientDTO>,

    val userIngredients: List<UserIngredientDTO>
)

fun MarketRecipeDTO.toMarketRecipeEntity(): MarketRecipeEntity {
    return MarketRecipeEntity(
        id = id,
        title = title,
        content = content,
        link = link,
        servings = servings,
        price = price,
        preparingMinutes = preparingMinutes,
        cookingMinutes = cookingMinutes,
        totalMinutes = totalMinutes,
        siteId = siteId,
        imageUrl = imageUrl
    )
}

data class MarketIngredientDTO (
    val ingredientId: Int,
    val ingredientName: String,
    val branchArticleId: Int,
    val usedQuantity: Int,
    val articleUnitQuantity: Int,
    val articleTotalQuantity: Int,
    val articlesQuantity: Int,
    val articleName: String,
    val unitPrice: Float,
    val totalPrice: Float,
    val branchId: Int,
    val recipeId: Int
)

fun MarketIngredientDTO.toMarketIngredientEntity(): MarketIngredientEntity {
    return MarketIngredientEntity(
        ingredientId = ingredientId,
        ingredientName = ingredientName,
        branchArticleId = branchArticleId,
        usedQuantity = usedQuantity,
        articleUnitQuantity = articleUnitQuantity,
        articleTotalQuantity = articleTotalQuantity,
        articlesQuantity = articlesQuantity,
        articleName = articleName,
        unitPrice = unitPrice,
        totalPrice = totalPrice,
        branchId = branchId,
        recipeId = recipeId
    )
}

data class UserIngredientDTO (
    val id: Int,
    val usedQuantity: Int
)