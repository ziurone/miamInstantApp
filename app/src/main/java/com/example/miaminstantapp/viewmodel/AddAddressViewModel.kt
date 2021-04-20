package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IAddUserAddressAction
import com.example.miaminstantapp.domain.actions.IFetchShopsAction
import com.example.miaminstantapp.domain.actions.RemoveAddressAction
import com.example.miaminstantapp.domain.entities.UserAddressEntity
import javax.inject.Inject

class AddAddressViewModel @Inject constructor(
    private val addUserAddressAction: IAddUserAddressAction,
    private val fetchShopsAction: IFetchShopsAction,
    private val removeAddressAction: RemoveAddressAction
): BaseViewModel<AddAddressViewModel.State>() {
    sealed class State {
        object AddedSuccess: State()
        object FetchShopsSuccess: State()
    }   

    init {
        listenSource(addUserAddressAction.getLiveData(), ::onAddressAdded)
        listenSource(fetchShopsAction.getLiveData(), ::onShopsFetchedSuccess)
        listenSource(removeAddressAction.getLiveData()) {}
    }

    private fun onAddressAdded(result: IAddUserAddressAction.Result) {
        when(result) {
            is IAddUserAddressAction.Result.Success -> setState(State.AddedSuccess)
            is IAddUserAddressAction.Result.Error -> TODO()
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

    fun removeAddresses() {
        removeAddressAction.remove()
    }

    fun fetchZoneShops(lat: String, long: String, squares: Int) {
        fetchShopsAction.fetchShopsAndBranches(lat, long, squares)
    }
}