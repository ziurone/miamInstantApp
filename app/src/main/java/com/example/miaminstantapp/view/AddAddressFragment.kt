package com.example.miaminstantapp.view

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.UserAddressEntity
import com.example.miaminstantapp.viewmodel.AddAddressViewModel
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.fragment_add_address.*
import kotlinx.android.synthetic.main.toolbar.*

class AddAddressFragment: BaseFragment<AddAddressViewModel, AddAddressViewModel.State>() {

    override fun getLayoutId(): Int = R.layout.fragment_add_address

    override fun initViews() {
        super.initViews()
        addAddress.keyListener = null
        addAddress.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                navigateToAddressComponent()
            }
        }
        toolbarClose.title = getString(R.string.step_3_of_3)


        toolbarClose.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
        toolbarClose.setNavigationOnClickListener { findNavController().popBackStack() }
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
        showAddress.isVisible = true
        addAddressLayout.isVisible = false
        addedAddress.text = place.address
        nextButton.isEnabled = true

        viewModel.removeAddresses()
        place.address?.let {
                address -> viewModel.addUserAddress(UserAddressEntity(address))
        }

        viewModel.fetchZoneShops(place.latLng?.latitude.toString(), place.latLng?.longitude.toString(), 10)

    }
}