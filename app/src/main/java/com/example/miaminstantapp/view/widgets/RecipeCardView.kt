package com.example.miaminstantapp.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.CatalogRecipe
import com.example.miaminstantapp.extensions.loadImageURL
import kotlinx.android.synthetic.main.recipe_card.view.*

class RecipeCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var catalogRecipe: CatalogRecipe

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.recipe_card, this, true)
    }

    fun setRecipe(catalogRecipe: CatalogRecipe) {
        this.catalogRecipe = catalogRecipe
        setImage()
        setTotalMinutes()
        setMissingIngredientsBadge()
        setIsFavorite()
        setName()
    }

    private fun setName() {
        recipeName.text = catalogRecipe.recipe.title
    }

    private fun setImage() {
        recipeImage.loadImageURL(catalogRecipe.recipe.imageUrl, R.drawable.placeholder_recipe_list_image)
    }

    private fun setTotalMinutes() {
        totalMinutesText.text = context.getString( R.string.total_minutes ,catalogRecipe.recipe.totalMinutes)
    }

    private fun setMissingIngredientsBadge() {
        if(catalogRecipe.marketIngredients.isNullOrEmpty()) {
            hasIngredientsBadge.isVisible = true
            missingIngredientsBadge.isVisible = false
        } else {
            hasIngredientsBadge.isVisible = false
            missingIngredientsBadge.isVisible = true
            missingIngredientsBadgeText.isVisible = true
            missingIngredientsBadgeText.text = context.getString(R.string.missing_ingredients_badge_text, catalogRecipe.marketIngredients.size, catalogRecipe.recipe.price)
        }
    }

    private fun setIsFavorite() {

    }

}