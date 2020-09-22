package com.example.miaminstantapp.view

import androidx.recyclerview.widget.GridLayoutManager
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.DietEntity
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.view.formatters.DietItemsPresenter
import com.example.miaminstantapp.view.items.DietItem
import com.example.miaminstantapp.viewmodel.userfilters.IAddUserDietsViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_add_user_diets.*

class AddUserDietsFragment: BaseFragment<IAddUserDietsViewModel, IAddUserDietsViewModel.State>() {

    private lateinit var dietsAdapter: GroupAdapter<GroupieViewHolder>
    private lateinit var diets: List<Diet>

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
            is IAddUserDietsViewModel.State.DietsFetched -> showDiets(state)
            IAddUserDietsViewModel.State.Loading -> {}
            is IAddUserDietsViewModel.State.UserDietsFetched -> createItems(state.diets)
        }
    }

    private fun createItems(userDiets: List<DietEntity>) {

        val dietItemPresenter = DietItemsPresenter(requireContext())
        val items = diets.map { DietItem(it, dietItemPresenter, userDiets) { isActive ->
            if(isActive) {
                viewModel.addUserDiet(it)
            } else {
                // TODO Add remove diet action.
            }
        }
        }
        dietsAdapter.update(items)
    }

    private fun showDiets(state: IAddUserDietsViewModel.State.DietsFetched) {
        diets = state.diets
    }
}