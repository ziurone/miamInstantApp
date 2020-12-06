package com.example.miaminstantapp.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.miaminstantapp.R
import com.example.miaminstantapp.viewmodel.EditUserIngredientViewModel

class EditUserIngredientFragment : BaseBottomSheetDialogFragment<EditUserIngredientViewModel, EditUserIngredientViewModel.State>() {

    private val screenArgs by navArgs<EditUserIngredientFragmentArgs>()

    companion object {
        fun newInstance() = EditUserIngredientFragment()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditUserIngredientViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.edit_user_ingredient_fragment

    override fun onStateChanged(state: EditUserIngredientViewModel.State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViews() {
        Log.i("BOTTOM_SHEET_ING_ID", screenArgs.ingredientId.toString())
        super.initViews()
    }

}
