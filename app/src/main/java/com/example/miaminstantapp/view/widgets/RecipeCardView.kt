package com.example.miaminstantapp.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import com.example.miaminstantapp.extensions.loadImageURL
import kotlinx.android.synthetic.main.recipe_card.view.*

class RecipeCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var catalogRecipeRelations: CatalogRecipeRelations

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.recipe_card, this, true)
    }

    fun setRecipe(catalogRecipeRelations: CatalogRecipeRelations) {
        this.catalogRecipeRelations = catalogRecipeRelations
        setImage()
        setTotalMinutes()
        setMissingIngredientsBadge()
        setIsFavorite()
        setName()
    }

    private fun setName() {
        recipeName.text = catalogRecipeRelations.recipe.title
    }

    private fun setImage() {
        recipeImage.loadImageURL(catalogRecipeRelations.recipe.imageUrl, R.drawable.placeholder_recipe_list_image)
    }

    private fun setTotalMinutes() {
        totalMinutesText.text = context.getString( R.string.total_minutes ,catalogRecipeRelations.recipe.totalMinutes)
    }

    private fun setMissingIngredientsBadge() {
        if(catalogRecipeRelations.marketIngredients.isNullOrEmpty()) {
            hasIngredientsBadge.isVisible = true
            missingIngredientsBadge.isVisible = false
        } else {
            hasIngredientsBadge.isVisible = false
            missingIngredientsBadge.isVisible = true
            missingIngredientsBadgeText.isVisible = true
            missingIngredientsBadgeText.text = context.getString(R.string.missing_ingredients_badge_text, catalogRecipeRelations.marketIngredients.size, catalogRecipeRelations.recipe.price)
        }
    }

    private fun setIsFavorite() {

    }

}