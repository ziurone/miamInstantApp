package com.example.miaminstantapp.view.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.miaminstantapp.R
import com.example.miaminstantapp.view.PresentationFragment
import com.example.miaminstantapp.view.PresentationFragment.Companion.DESCRIPTION_KEY
import com.example.miaminstantapp.view.PresentationFragment.Companion.ENABLE_BUTTON_KEY
import com.example.miaminstantapp.view.PresentationFragment.Companion.ICON_KEY

class PresentationFragmentAdapter (val fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val bundle = when(position) {
            0 -> bundleOf(DESCRIPTION_KEY to  fa.getString(R.string.presentation_first_step_text), ICON_KEY to R.drawable.ic_cheff, ENABLE_BUTTON_KEY to false )
            1 -> bundleOf(DESCRIPTION_KEY to fa.getString(R.string.presentation_second_step_text),ICON_KEY to R.drawable.ic_fridge_cheff, ENABLE_BUTTON_KEY to false )
            2 -> bundleOf(DESCRIPTION_KEY to fa.getString(R.string.presentation_third_step_text), ICON_KEY to R.drawable.ic_recipe_book_ingredients, ENABLE_BUTTON_KEY to false  )
            3 -> bundleOf(DESCRIPTION_KEY to fa.getString(R.string.presentation_fourth_step_text), ICON_KEY to R.drawable.ic_shopping_cart_cheff, ENABLE_BUTTON_KEY to true )
            else -> bundleOf(DESCRIPTION_KEY to "etc etc" )
        }

        val fragment = PresentationFragment()
        fragment.arguments = bundle
        return fragment
    }
}