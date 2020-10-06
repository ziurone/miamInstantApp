package com.example.miaminstantapp.view.items

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class UserIngredientItem constructor(
    private val userIngredient: UserIngredientEntity,
    private val deleteIconClickListemer: () -> Unit
): Item<UserIngredientItem.UserIngredientItemViewHolder>() {

    override fun createViewHolder(itemView: View): UserIngredientItemViewHolder = UserIngredientItemViewHolder(itemView)

    class UserIngredientItemViewHolder(view: View): GroupieViewHolder(view) {
        val name: TextView = view.findViewById(R.id.ingredientName)
        val ingredientDefaultQuantity: TextView = view.findViewById(R.id.ingredientDefaultQuantity)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }

    override fun isSameAs(other: Item<*>): Boolean {
        return when(other) {
            is UserIngredientItem -> userIngredient.ingredientId == other.userIngredient.ingredientId
            else -> false
        }
    }

    override fun getLayout(): Int = R.layout.item_user_ingredient

    override fun bind(viewHolder: UserIngredientItemViewHolder, position: Int) {
        viewHolder.name.text = userIngredient.name
        viewHolder.ingredientDefaultQuantity.text = userIngredient.volumeUnitQuantity.toString()
        viewHolder.deleteButton.setOnClickListener { deleteIconClickListemer() }
    }
}