package com.example.miaminstantapp.domain.dtos

import android.os.IInterface
import com.example.miaminstantapp.domain.entities.*
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
    val imageUrl: String?,
    val marketIngredients: List<MarketIngredientDTO>,
    val userIngredients: List<CatalogRecipeUserIngredientDTO>
) {
    fun toCatalogRecipeEntity() = CatalogRecipeEntity(
        id, title, content, preparingMinutes, cookingMinutes, totalMinutes, imageUrl
    )
}

@JsonClass(generateAdapter = true)
data class MarketIngredientDTO(
    val ingredient: Ingredient,
    val usedQuantity: Double,
    val volumeUnitId: Int
) {
    fun toCatalogRecipeMarketIngredientEntity(recipeId: Int) = CatalogRecipeMarketIngredientEntity(
        ingredient.id,
        ingredient.name,
        volumeUnitId,
        usedQuantity,
        recipeId
    )
}

@JsonClass(generateAdapter = true)
data class CatalogRecipeUserIngredientDTO(
    val id: Int,
    val name: String,
    val usedQuantity: Double,
    val volumeUnitId: Int
) {
    fun toCatalogRecipeUserIngredientEntity(recipeId: Int) = CatalogRecipeUserIngredientEntity(
        ingredientId = id,
        ingredientName = name,
        usedQuantity = usedQuantity,
        volumeUnitId = volumeUnitId,
        recipeId = recipeId
    )
}

fun MarketRecipeDTOLegacy.toMarketRecipeEntity(): CatalogRecipeEntityLegacy {
    return CatalogRecipeEntityLegacy(
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
    val usedQuantity: Double,
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