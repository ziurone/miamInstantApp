package com.example.miaminstantapp.view.recipedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.miaminstantapp.R
import com.example.miaminstantapp.extensions.setFullScreen
import dagger.android.support.DaggerAppCompatActivity

class CatalogRecipeDetailActivity: DaggerAppCompatActivity() {

    private lateinit var navigationController: NavController

    companion object {

        const val CATALOG_RECIPE_ID_KEY = "catalogRecipeIdKey"

        private fun getCallingIntent(context: Context, recipeId: Int): Intent {
            return Intent(context, CatalogRecipeDetailActivity::class.java).apply {
                val bundle = CatalogRecipeDetailFragmentArgs(
                    catalogRecipeIdKey = recipeId
                ).toBundle()
             putExtras(bundle)
            }
        }

        fun startActivity(context: Context, recipeId: Int) {
            context.startActivity(getCallingIntent(context, recipeId))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_catalog_recipe_detail)
        this.setFullScreen()
        navigationController = findNavController(R.id.catalogRecipeDetailFragmentHost).apply {
            setGraph(R.navigation.catalog_recipe_detail_graph, intent.extras)
        }
    }



}