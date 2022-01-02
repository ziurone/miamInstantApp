package com.example.miaminstantapp.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
import com.example.miaminstantapp.extensions.loadImageURL
import kotlinx.android.synthetic.main.recipe_card.view.*

class RecipeCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var catalogRecipeRelationsLegacy: CatalogRecipeRelationsLegacy

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.recipe_card, this, true)
    }

    fun setRecipe(catalogRecipeRelationsLegacy: CatalogRecipeRelationsLegacy, showMissingIngredients: Boolean) {
        this.catalogRecipeRelationsLegacy = catalogRecipeRelationsLegacy
        setImage()
        setTotalMinutes()
        setMissingIngredientsBadge(showMissingIngredients)
        setIsFavorite()
        setName()
        setPrice()
    }

    private fun setName() {
        recipeName.text = catalogRecipeRelationsLegacy.recipeLegacy.title
    }

    private fun setImage() {
        recipeImage.loadImageURL(catalogRecipeRelationsLegacy.recipeLegacy.imageUrl, R.drawable.ic_recipe_card_empty_image)
    }

    private fun setTotalMinutes() {
        totalMinutesText.text = context.getString( R.string.total_minutes ,catalogRecipeRelationsLegacy.recipeLegacy.totalMinutes)
    }

    private fun setPrice() {
//        recipePrice.text = context.getString(R.string.recipe_card_price, catalogRecipeRelations.recipe.price.toInt())
    }

    private fun setMissingIngredientsBadge(show: Boolean) {
        if(show) {
            if(catalogRecipeRelationsLegacy.marketIngredients.isNullOrEmpty()) {
                missingIngredients.text = context.getString(R.string.card_recipe_fulfilled_text)
                missingIngredients.setTextColor(resources.getColor(R.color.secondary_light_700))
            } else {
                missingIngredients.text = context.getString(R.string.missing_ingredients_badge_text, catalogRecipeRelationsLegacy.marketIngredients.size)
                missingIngredients.setTextColor(resources.getColor(R.color.onError))
            }
        } else {
            missingIngredients.visibility = View.GONE
        }

    }

    private fun setIsFavorite() {

    }

}