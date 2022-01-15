package com.example.miaminstantapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.miaminstantapp.R
import dagger.android.support.DaggerAppCompatActivity

class ShoppingCartActivity: DaggerAppCompatActivity() {

    private lateinit var navigationController: NavController

    companion object {
        private fun getCallingIntent(context: Context): Intent {
            return Intent(context, ShoppingCartActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(getCallingIntent(context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shopping_cart)

        navigationController = findNavController(R.id.shoppingCartFragmentHost).apply {
            setGraph(R.navigation.shopping_cart_graph, intent.extras)
        }
    }
}