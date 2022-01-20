package com.example.miaminstantapp.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.MarketIngredientRelations
import com.example.miaminstantapp.extensions.loadImageURL
import kotlinx.android.synthetic.main.recipe_card.view.*

class RecipeCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.recipe_card, this, true)
    }

    fun setRecipe(catalogRecipeAggregate: CatalogRecipeAgreggate, showMissingIngredients: Boolean) {
        setImage(catalogRecipeAggregate.recipe.imageUrl)
        setTotalMinutes(catalogRecipeAggregate.recipe.totalMinutes)
        setMissingIngredientsBadge(showMissingIngredients, catalogRecipeAggregate.marketIngredients)
        setName(catalogRecipeAggregate.recipe.title)
    }

    fun setRecipeBookRecipe(recipeBookRecipe: RecipeBookRecipeEntity) {
        missingIngredients.visibility = View.GONE
        setImage(recipeBookRecipe.imageUrl)
        setTotalMinutes(recipeBookRecipe.totalMinutes)
        setMissingIngredientsBadge(false, listOf())
        setName(recipeBookRecipe.name)
    }

    private fun setName(name: String) {
        recipeName.text = name
    }

    private fun setImage(imageUrl: String?) {
        recipeImage.loadImageURL(imageUrl.orEmpty(), R.drawable.ic_recipe_card_empty_image)
    }

    private fun setTotalMinutes(totalMinutes: Int) {
        totalMinutesText.text = context.getString( R.string.total_minutes , totalMinutes)
    }

    private fun setPrice() {
//        recipePrice.text = context.getString(R.string.recipe_card_price, catalogRecipeRelations.recipe.price.toInt())
    }

    private fun setMissingIngredientsBadge(show: Boolean, marketIngredients: List<MarketIngredientRelations>) {
        if(show) {
            if(marketIngredients.isNullOrEmpty()) {
                missingIngredients.text = context.getString(R.string.card_recipe_fulfilled_text)
                missingIngredients.setTextColor(resources.getColor(R.color.secondary_light_700))
            } else {
                missingIngredients.text = context.getString(R.string.missing_ingredients_badge_text, marketIngredients.size)
                missingIngredients.setTextColor(resources.getColor(R.color.onError))
            }
        } else {
            missingIngredients.visibility = View.GONE
        }

    }

    private fun setIsFavorite() {

    }

}