package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity
import com.squareup.moshi.JsonClass

data class MarketRecipeDTOLegacy(
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
    val marketIngredientLegacies: List<MarketIngredientDTOLegacy>,
    val userIngredientLegacies: List<UserIngredientDTOLegacy>
)

data class CatalogRecipeDto(
    val id: Int,
    val title: String,
    val content: String,
    val preparingMinutes: Int,
    val cookingMinutes: Int,
    val totalMinutes: Int,
    val imageUrl: String,
    val marketIngredients: List<MarketIngredientDTO>,
    val userIngredients: List<RecipeUserIngredientDTO>
)

@JsonClass(generateAdapter = true)
data class MarketIngredientDTO(
    val ingredient: Ingredient,
    val usedQuantity: Int,
    val usedVolumeUnitQuantityId: Int
)

data class RecipeUserIngredientDTO(
    val id: Int,
    val usedQuantity: Int,
    val usedVolumeUnitQuantityId: Int
)

fun MarketRecipeDTOLegacy.toMarketRecipeEntity(): CatalogRecipeEntity {
    return CatalogRecipeEntity(
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

data class MarketIngredientDTOLegacy (
    val ingredientId: Int,
    val ingredientName: String,
    val volumeUnitId: Int,
    val volumeUnitQuantity: Float,
    val branchArticleId: Int,
    val usedQuantity: Int,
    val articleUnitQuantity: Int,
    val articleTotalQuantity: Int,
    val articlesQuantity: Int,
    val articleName: String,
    val unitPrice: Float,
    val totalPrice: Float,
    val branchId: Int,
    val shopName: String,
    val recipeId: Int,
    val imageUrl: String
)

fun MarketIngredientDTOLegacy.toMarketIngredientEntity(): MarketIngredientEntityLegacy {
    return MarketIngredientEntityLegacy(
        ingredientId = ingredientId,
        ingredientName = ingredientName,
        volumeUnitId = volumeUnitId,
        volumeUnitQuantity = volumeUnitQuantity,
        branchArticleId = branchArticleId,
        usedQuantity = usedQuantity,
        articleUnitQuantity = articleUnitQuantity,
        articleTotalQuantity = articleTotalQuantity,
        articlesQuantity = articlesQuantity,
        articleName = articleName,
        unitPrice = unitPrice,
        totalPrice = totalPrice,
        branchId = branchId,
        shopName = shopName,
        recipeId = recipeId
    )
}

data class UserIngredientDTOLegacy (
    val id: Int,
    val name: String,
    val usedQuantity: Int,
    val volumeUnitId: Int,
    val volumeUnitQuantity: Float
)