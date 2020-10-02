package com.example.miaminstantapp.view

import android.content.Intent
import androidx.core.view.isVisible
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.UserAddressEntity
import com.example.miaminstantapp.viewmodel.AddAddressViewModel
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.fragment_add_address.*

class AddAddressFragment: BaseFragment<AddAddressViewModel, AddAddressViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_add_address

    override fun initViews() {
        super.initViews()
        addAddress.setOnClickListener {
            navigateToAddressComponent()
        }
    }

    override fun onStateChanged(state: AddAddressViewModel.State) {
        when(state) {
            AddAddressViewModel.State.AddedSuccess -> {}
            AddAddressViewModel.State.FetchShopsSuccess -> {}
        }
    }

    private fun navigateToAddressComponent() {
        val autocompleteFields = listOf(
            Place.Field.ID,
            Place.Field.ADDRESS)

        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN,
            autocompleteFields
        ).build(context!!)


        startActivityForResult(intent,
            DispensaryFragment.AUTOCOMPLETE_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            DispensaryFragment.AUTOCOMPLETE_REQUEST_CODE -> showSelectedAddress(data)
        }
    }

    private fun showSelectedAddress(data: Intent?) {
        val place = Autocomplete.getPlaceFromIntent(data!!)
        userAddress.text = place.address

        place.address?.let {
                address -> viewModel.addUserAddress(UserAddressEntity(address))
        }

        viewModel.fetchZoneShops(place.latLng?.latitude.toString(), place.latLng?.longitude.toString(), 10)

    }
}