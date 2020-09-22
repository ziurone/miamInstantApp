package com.example.miaminstantapp.view.items

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.DietEntity
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.view.formatters.DietItemsPresenter
import com.example.miaminstantapp.view.items.DietItem.DietItemViewHolder
import com.google.android.material.card.MaterialCardView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class DietItem(
    private val diet: Diet,
    private val dietItemsPresenter: DietItemsPresenter,
    private val userDiets: List<DietEntity>,
    private val addOrRemoveItemClickListener: (isActive: Boolean) -> Unit
) : Item<DietItemViewHolder>() {

    private var isActive = false

    override fun getLayout(): Int = R.layout.item_diet

    override fun bind(viewHolder: DietItemViewHolder, position: Int) {
        viewHolder.name.text = dietItemsPresenter.getTitle(diet)
        viewHolder.description.text = dietItemsPresenter.getDescription(diet)
        viewHolder.icon.setImageResource(dietItemsPresenter.getIcon(diet))
        isActive = userDiets.filter { userDiet -> diet.name == userDiet.name }.isNotEmpty()
        viewHolder.parent.setBackgroundColor(dietItemsPresenter.getBackground(isActive))
        viewHolder.parent.setOnClickListener {
            isActive = !isActive
            viewHolder.parent.setBackgroundColor(dietItemsPresenter.getBackground(isActive))
            addOrRemoveItemClickListener(isActive)
        }
    }

    override fun createViewHolder(itemView: View) = DietItemViewHolder(itemView)

    class DietItemViewHolder(view: View): GroupieViewHolder(view) {
        val name: TextView = view.findViewById(R.id.dietName)
        val description: TextView = view.findViewById(R.id.dietDescription)
        val icon: ImageView = view.findViewById(R.id.dietIcon)
        val parent: MaterialCardView = view.findViewById(R.id.dietItemParent)
    }
}