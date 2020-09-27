package com.example.miaminstantapp.view.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener

class ShortIngredientItem(
    private val shortIngredient: IngredientShortDto,
    private val itemClickListener: (shortIngredient: IngredientShortDto) -> Unit
): Item<ShortIngredientItem.ShortIngredientItemViewHolder>() {

    class ShortIngredientItemViewHolder(view: View): GroupieViewHolder(view) {
        val name: TextView = view.findViewById(R.id.ingredientName)
    }

    override fun getLayout(): Int = R.layout.item_ingredient_short

    override fun createViewHolder(itemView: View): ShortIngredientItemViewHolder = ShortIngredientItemViewHolder(itemView)

    override fun bind(viewHolder: ShortIngredientItemViewHolder, position: Int) {
        viewHolder.name.text = shortIngredient.name

        viewHolder.name.setOnClickListener {
            itemClickListener(shortIngredient)
        }
    }
}