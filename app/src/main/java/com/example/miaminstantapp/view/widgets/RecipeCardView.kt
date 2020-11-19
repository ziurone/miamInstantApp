package com.example.miaminstantapp.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.DoableRecipe
import com.example.miaminstantapp.extensions.loadImageURL
import kotlinx.android.synthetic.main.recipe_card.view.*

class RecipeCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var doableRecipe: DoableRecipe

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.recipe_card, this, true)
    }

    fun setRecipe(doableRecipe: DoableRecipe) {
        this.doableRecipe = doableRecipe
        setImage()
        setTotalMinutes()
        setMissingIngredientsBadge()
        setIsFavorite()
        setName()
    }

    private fun setName() {
        recipeName.text = doableRecipe.recipe.title
    }

    private fun setImage() {
        recipeImage.loadImageURL(doableRecipe.recipe.imageUrl)
    }

    private fun setTotalMinutes() {
        totalMinutesText.text = context.getString( R.string.total_minutes ,doableRecipe.recipe.totalMinutes)
    }

    private fun setMissingIngredientsBadge() {
        if(doableRecipe.marketIngredients.isNullOrEmpty()) {
            hasIngredientsBadge.isVisible = true
            missingIngredientsBadge.isVisible = false
        } else {
            hasIngredientsBadge.isVisible = false
            missingIngredientsBadge.isVisible = true
            missingIngredientsBadgeText.isVisible = true
            missingIngredientsBadgeText.text = context.getString(R.string.missing_ingredients_badge_text, doableRecipe.marketIngredients.size, doableRecipe.recipe.price)
        }
    }

    private fun setIsFavorite() {

    }

}