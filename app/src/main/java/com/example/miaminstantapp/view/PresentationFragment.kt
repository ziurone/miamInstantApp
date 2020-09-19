package com.example.miaminstantapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.miaminstantapp.R
import com.example.miaminstantapp.routing.Launcher
import kotlinx.android.synthetic.main.fragment_presentation.*


class PresentationFragment: Fragment() {

    companion object {
        const val DESCRIPTION_KEY = "descriptionKey"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_presentation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            description.text = it.getString(DESCRIPTION_KEY, "")
        }

        nextFlow.setOnClickListener {
            Launcher(requireContext()).filtersDislikeIngredients()
        }

    }

}