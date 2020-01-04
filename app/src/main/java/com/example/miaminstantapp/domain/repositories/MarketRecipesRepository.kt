package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTO
import com.example.miaminstantapp.domain.dtos.MarketRecipeDTO
import com.example.miaminstantapp.domain.dtos.UserIngredientDTO
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import com.example.miaminstantapp.domain.entities.RecipeWithUserIngredients
import com.example.miaminstantapp.persistence.MarketRecipeDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class MarketRecipesRepository @Inject constructor(
    private val marketRecipeDao: MarketRecipeDao
): IMarketRecipesRepository {

    override fun insertAll(recipes: List<MarketRecipeEntity>): Completable {
        return marketRecipeDao.insertAll(recipes)
    }

    override fun fetchSearchRecipes(): Single<List<RecipeWithUserIngredients>> = marketRecipeDao.fetchAll()

    override fun search(): Single<List<MarketRecipeDTO>> {
        return Single.just(
            listOf(
                MarketRecipeDTO(
                    1,
                    "Pollo con ensalada",
                    "Preparar pollo con ensalada",
                    "http://receta",
                    4,
                    1.50f,
                    10,
                    10,
                    20,
                    1,
                    "http://image",
                    listOf(
                        MarketIngredientDTO(
                            1,
                            "pollo",
                            2,
                            100,
                            1,
                            1,
                            1,
                            "pollo coto",
                            100f,
                            100f,
                            2,
                            1
                        )
                    ),
                    listOf(
                        UserIngredientDTO(
                            1,
                            100
                        )
                    )
                )
            )
        )
    }
}