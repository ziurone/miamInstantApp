package com.example.miaminstantapp.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.miaminstantapp.viewmodel.EditUserIngredientViewModel

class EditUserIngredientFragment : BaseBottomSheetDialogFragment<EditUserIngredientViewModel, EditUserIngredientViewModel.State>() {

    companion object {
        fun newInstance() = EditUserIngredientFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditUserIngredientViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStateChanged(state: EditUserIngredientViewModel.State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
