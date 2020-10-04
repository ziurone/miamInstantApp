package com.example.miaminstantapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.miaminstantapp.view.NavigationActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    companion object {
        private fun getCallingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(getCallingIntent(context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomAppBar.inflateMenu(R.menu.bottom_app_bar_menu)
        bottomAppBar.setOnMenuItemClickListener{ menuItem ->
                when(menuItem.itemId) {
                    R.id.appBarRecipeList -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.action_global_market_recipes)
                        true
                    }
                    R.id.appBarRecipeBook -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.global_action_toRecipeBookFragment)
                        true
                    }
                    R.id.appBarShoppingCart -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.action_global_toShopPurchase)
                        true
                    }
                    else -> false
                }
        }
    }
}
