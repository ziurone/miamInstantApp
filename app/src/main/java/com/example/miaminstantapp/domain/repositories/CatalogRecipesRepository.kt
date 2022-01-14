package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.*
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntityLegacy
import com.example.miaminstantapp.domain.entities.CatalogRecipeMarketIngredientEntity
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.persistence.CatalogRecipeDao
import com.example.miaminstantapp.persistence.MarketIngredientDao
import com.example.miaminstantapp.persistence.CatalogRecipeUserIngredientDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class CatalogRecipesRepository @Inject constructor(
    private val catalogRecipeDao: CatalogRecipeDao,
    private val marketIngredientDao: MarketIngredientDao,
    private val catalogRecipeUserIngredientDao: CatalogRecipeUserIngredientDao
): ICatalogRecipesRepository {

    override fun insertAllLegacy(recipeLegacies: List<CatalogRecipeEntityLegacy>): Completable {
        return catalogRecipeDao.insertAllLegacy(recipeLegacies)
    }

    override fun insertAll(recipes: List<CatalogRecipeDto>): Completable {
        return catalogRecipeDao
            .insertAll(recipes.map { it.toCatalogRecipeEntity() })
            .andThen( run {
                val marketIngredients = mutableListOf<CatalogRecipeMarketIngredientEntity>()

                recipes.forEach { r -> r.marketIngredients.forEach { marketIngredients.add(it.toCatalogRecipeMarketIngredientEntity(r.id)) } }

                marketIngredientDao.insertAll(marketIngredients)
            } )
            .andThen ( run {
                val userIngredients = mutableListOf<CatalogRecipeUserIngredientEntity>()

                recipes.forEach { r -> r.userIngredients.forEach { userIngredients.add(it.toCatalogRecipeUserIngredientEntity(r.id)) } }

                catalogRecipeUserIngredientDao.insertAll(userIngredients)

            } )
    }

    override fun deleteAll(): Completable  = catalogRecipeDao.deleteAll()

    override fun fetchRecipeById(id: Int): Single<CatalogRecipeAgreggate> = catalogRecipeDao.fetchById(id)

    override fun fetchSearchRecipes(): Single<List<CatalogRecipeAgreggate>> = catalogRecipeDao.fetchAll()

    override fun search(searchCriteria: RecipeSearchCriteria): Single<List<CatalogRecipeDto>> {
        val recipe1 = CatalogRecipeDto(
                1,
            "Pastel de papas",
            "Preparar pastel de papas",
            10,
            10,
            20,
            null,
            listOf(
                MarketIngredientDTO(
                    ingredient = Ingredient(
                        100,
                        "papa",
                        1,
                        1000,
                        listOf(1,2)
                    ),
                    volumeUnitId = 1,
                    usedQuantity = 100
                )
            ),
            listOf(CatalogRecipeUserIngredientDTO(
                id = 100,
                name = "Batata",
                usedQuantity = 2,
                volumeUnitId = 1
            )
            )
        )

        val apiRecipes = listOf(recipe1)
        val apiRecipesSingle = Single.just(apiRecipes)

        return apiRecipesSingle
    }

    override fun searchLegacy(searchCriteria: RecipeSearchCriteria): Single<List<MarketRecipeDTOLegacy>> {

        val searchWithMoneyResponse = Single.just(
            listOf(
                MarketRecipeDTOLegacy(
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
                    "",
                    listOf(
                        MarketIngredientDTOLegacy(
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
                        MarketIngredientDTOLegacy(
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
                        MarketIngredientDTOLegacy(
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
                        MarketIngredientDTOLegacy(
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
                MarketRecipeDTOLegacy(
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
                    "",
                    listOf(
                        MarketIngredientDTOLegacy(
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
                        UserIngredientDTOLegacy(
                            1,
                            "sal",
                            100,
                            3,
                            1f
                        )
                    )
                )
            )
        )
    }
}