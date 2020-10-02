package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IAddUserAddressAction
import com.example.miaminstantapp.domain.actions.IFetchShopsAction
import com.example.miaminstantapp.domain.entities.UserAddressEntity
import javax.inject.Inject

class AddAddressViewModel @Inject constructor(
    private val addUserAddressAction: IAddUserAddressAction,
    private val fetchShopsAction: IFetchShopsAction
): BaseViewModel<AddAddressViewModel.State>() {
    sealed class State {
        object AddedSuccess: State()
        object FetchShopsSuccess: State()
    }

    init {
        listenSource(addUserAddressAction.getLiveData(), ::onAddressAdded)
        listenSource(fetchShopsAction.getLiveData(), ::onShopsFetchedSuccess)
    }

    private fun onAddressAdded(result: IAddUserAddressAction.Result) {
        when(result) {
            is IAddUserAddressAction.Result.Success -> setState(State.AddedSuccess)
        }
    }

    private fun onShopsFetchedSuccess(result: IFetchShopsAction.Result){
        when(result) {
            IFetchShopsAction.Result.Success -> {}
            IFetchShopsAction.Result.Error -> {}
        }
    }

    fun addUserAddress(address: UserAddressEntity) {
        addUserAddressAction.add(address)
    }

    fun fetchZoneShops(lat: String, long: String, squares: Int) {
        fetchShopsAction.fetchShopsAndBranches(lat, long, squares)
    }
}