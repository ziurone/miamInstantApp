package com.example.miaminstantapp.view.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.miaminstantapp.view.PresentationFragment
import com.example.miaminstantapp.view.PresentationFragment.Companion.DESCRIPTION_KEY

class PresentationFragmentAdapter (fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val bundle = when(position) {
            0 -> bundleOf(DESCRIPTION_KEY to "Hola soy el chef" )
            1 -> bundleOf(DESCRIPTION_KEY to "Presentacion ingredientes" )
            2 -> bundleOf(DESCRIPTION_KEY to "Presentacion compra" )
            else -> bundleOf(DESCRIPTION_KEY to "etc etc" )
        }

        val fragment = PresentationFragment()
        fragment.arguments = bundle
        return fragment
    }
}