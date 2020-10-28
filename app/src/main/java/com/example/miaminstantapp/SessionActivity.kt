package com.example.miaminstantapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : DaggerAppCompatActivity() {

    companion object {
        private fun getCallingIntent(context: Context): Intent {
            return Intent(context, SessionActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(getCallingIntent(context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        bottomAppBar.setOnNavigationItemSelectedListener{ menuItem ->
                when(menuItem.itemId) {
                    R.id.appBarRecipeList -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.action_global_market_recipes)
                        true
                    }
                    R.id.appBarFavorites -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.global_action_toRecipeBookFragment)
                        true
                    }
                    R.id.appBarShoppingCart -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.action_global_toShopPurchase)
                        true
                    }
                    R.id.dispensary -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.global_action_toDispensaryFragment)
                        true
                    }
                    R.id.profile -> {
                        findNavController(R.id.userFiltersFragmentHost).navigate(R.id.global_action_toDispensaryFragment)
                        true
                    }
                    else -> false
                }
        }
    }
}
