package com.example.miaminstantapp.viewmodel.userfilters

import com.example.miaminstantapp.domain.actions.DietAction
import com.example.miaminstantapp.domain.enums.Diet
import javax.inject.Inject

class AddUserDietsViewModel @Inject constructor(
    private val dietAction: DietAction
): IAddUserDietsViewModel() {

    init {
        listenSource(dietAction.getLiveData(), ::addedSuccess)
    }

    private fun addedSuccess(result: DietAction.Result) {
        when(result) {
            is DietAction.Result.FetchUserDietsSuccess -> setState(State.UserDietsFetched(result.diets))
        }
    }

    override fun addUserDiet(diet: Diet) {
        dietAction.addDiet(diet)
    }

    override fun fetchDiets() {
        setState(State.DietsFetched(dietAction.getDiets()))
        dietAction.getUserDiets()
    }

}