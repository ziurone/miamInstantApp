package com.example.miaminstantapp.view.items

import android.view.View
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.view.items.DietItem.DietItemViewHolder
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class DietItem(
    private val diet: Diet
) : Item<DietItemViewHolder>() {

    override fun getLayout(): Int = R.layout.item_diet

    override fun bind(viewHolder: DietItemViewHolder, position: Int) {
        viewHolder.name.text = diet.name
    }

    override fun createViewHolder(itemView: View) = DietItemViewHolder(itemView)

    class DietItemViewHolder(view: View): GroupieViewHolder(view) {
        val name: TextView = view.findViewById(R.id.dietName)
    }
}