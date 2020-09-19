package com.example.miaminstantapp.view

import androidx.recyclerview.widget.GridLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.view.items.DietItem
import com.example.miaminstantapp.viewmodel.userfilters.IAddUserDietsViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_add_user_diets.*

class AddUserDietsFragment: BaseFragment<IAddUserDietsViewModel, IAddUserDietsViewModel.State>() {

    private lateinit var dietsAdapter: GroupAdapter<GroupieViewHolder>

    override fun initViews() {
        super.initViews()
        viewModel.fetchDiets()
        dietsAdapter = GroupAdapter()
        dietsList.apply {
            adapter = dietsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_add_user_diets

    override fun onStateChanged(state: IAddUserDietsViewModel.State) {
        when(state) {
            is IAddUserDietsViewModel.State.DietsFetched -> {
                val items = state.diets.map { DietItem(it) }
                dietsAdapter.update(items)
            }
            IAddUserDietsViewModel.State.Loading -> {}
        }
    }
}