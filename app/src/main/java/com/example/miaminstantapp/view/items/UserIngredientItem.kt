package com.example.miaminstantapp.view.items

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class UserIngredientItem constructor(
    private val userIngredient: UserIngredientWithVolumeUnits,
    private val context: Context,
    private val deleteIconClickListemer: () -> Unit
): Item<UserIngredientItem.UserIngredientItemViewHolder>() {

    override fun createViewHolder(itemView: View): UserIngredientItemViewHolder = UserIngredientItemViewHolder(itemView)

    class UserIngredientItemViewHolder(view: View): GroupieViewHolder(view) {
        val name: TextView = view.findViewById(R.id.ingredientName)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }

    override fun isSameAs(other: Item<*>): Boolean {
        return when(other) {
            is UserIngredientItem -> userIngredient.ingredient.ingredientId == other.userIngredient.ingredient.ingredientId
            else -> false
        }
    }

    override fun getLayout(): Int = R.layout.item_user_ingredient

    override fun bind(viewHolder: UserIngredientItemViewHolder, position: Int) {
        viewHolder.name.text = userIngredient.ingredient.name.capitalize()
        // Code for mapping default volume unit to be shown if needed.
//        val defaultVolumeUnit = userIngredient.volumeUnits.filter { userIngredient.ingredient.volumeUnitId == it.volumeUnitId }[0]

        viewHolder.deleteButton.setOnClickListener { deleteIconClickListemer() }
    }
}