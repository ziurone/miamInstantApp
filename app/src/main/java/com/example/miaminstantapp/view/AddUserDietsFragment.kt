package com.example.miaminstantapp.view

import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
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
import kotlinx.android.synthetic.main.toolbar.*

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

        toolbarClose.title = getString(R.string.step_1_of_3)
        toolbarClose.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
        toolbarClose.setNavigationOnClickListener { activity?.onBackPressed() }

        next.setOnClickListener {
            findNavController().navigate(R.id.from_addUserDiets_to_AddUserAlergies)
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
        val items = diets.map { DietItem(it, dietItemPresenter, userDiets) {
                isActive ->
                if(it != Diet.NONE) {
                    if(isActive) {
                        viewModel.addUserDiet(it)
                    } else {
                        viewModel.removeDiet(it)
                    }
                }
            }
        }
        dietsAdapter.update(items)
    }

    private fun showDiets(state: IAddUserDietsViewModel.State.DietsFetched) {
        diets = state.diets
    }
}