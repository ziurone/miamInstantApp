package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTO
import com.example.miaminstantapp.domain.dtos.MarketRecipeDTO
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.dtos.UserIngredientDTO
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations
import com.example.miaminstantapp.persistence.CatalogRecipeDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class MarketRecipesRepository @Inject constructor(
    private val catalogRecipeDao: CatalogRecipeDao
): IMarketRecipesRepository {

    override fun insertAll(recipes: List<CatalogRecipeEntity>): Completable {
        return catalogRecipeDao.insertAll(recipes)
    }

    override fun deleteAll(): Completable  = catalogRecipeDao.deleteAll()

    override fun fetchRecipeById(id: Int): Single<CatalogRecipeRelations> = catalogRecipeDao.fetchById(id)

    override fun fetchSearchRecipes(): Single<List<CatalogRecipeRelations>> = catalogRecipeDao.fetchAll()

    override fun search(searchCriteria: RecipeSearchCriteria): Single<List<MarketRecipeDTO>> {

        val searchWithMoneyResponse = Single.just(
            listOf(
                MarketRecipeDTO(
                    1,
                    "Pollo relleno",
                    "Preparar pollo y rellenar",
                    "https://cocinerosargentinos.com/aves/pollo-relleno",
                    4,
                    1.50f,
                    20,
                    30,
                    50,
                    1,
                    "https://cocinerosargentinos.com/content/recipes/thumb/recipes.11439.jpeg",
                    listOf(
                        MarketIngredientDTO(
                            7,
                            "pollo deshuesado",
                            3,
                            1f,
                            100,
                            1000,
                            1000,
                            1,
                            1000,
                            "Pollo coto",
                            100f,
                            100f,
                            1,
                            "coto",
                            1,
                            "https://imagenes.preciosclaros.gob.ar/productos/categorias/060104004.jpg"
                        ),
                        MarketIngredientDTO(
                            8,
                            "queso muzzarela",
                            3,
                            0.2f,
                            101,
                            400,
                            500,
                            1,
                            500,
                            "Queso Mozzarella al Oregano 500 Gr",
                            278f,
                            278f,
                            1,
                            "coto",
                            1,
                            "https://imagenes.preciosclaros.gob.ar/productos/categorias/060608007.jpg"
                        ),
                        MarketIngredientDTO(
                            9,
                            "jamon cocido",
                            3,
                            0.2f,
                            102,
                            200,
                            200,
                            1,
                            200,
                            "Jamon Cocido Natural Feteado La Comarca 200 Gr",
                            207.65f,
                            207.65f,
                            1,
                            "coto",
                            1,
                            "https://imagenes.preciosclaros.gob.ar/productos/categorias/060302018.jpg"
                        ),
                        MarketIngredientDTO(
                            10,
                            "puerro",
                            3,
                            0.2f,
                            103,
                            100,
                            300,
                            1,
                            300,
                            "Puerro 30 gr",
                            34.9f,
                            34.9f,
                            1,
                            "coto",
                            1,
                            "https://imagenes.preciosclaros.gob.ar/productos/categorias/060908001.jpg"
                        )
                    ),
                    listOf()
                )
            )
        )

        if(searchCriteria.userMoney > 0) {
            return searchWithMoneyResponse
        }

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
                    "https://images.media-allrecipes.com/userphotos/720x405/667748.jpg",
                    listOf(
                        MarketIngredientDTO(
                            1,
                            "pollo",
                            3,
                            1f,
                            2,
                            100,
                            1,
                            1,
                            1,
                            "pollo coto",
                            100f,
                            100f,
                            2,
                            "coto",
                            1,
                            "https://imagenes.preciosclaros.gob.ar/productos/categorias/060104004.jpg"
                        )
                    ),
                    listOf(
                        UserIngredientDTO(
                            1,
                            "sal",
                            100
                        )
                    )
                )
            )
        )
    }
}