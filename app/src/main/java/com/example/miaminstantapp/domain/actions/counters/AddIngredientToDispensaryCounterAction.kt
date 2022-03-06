package com.example.miaminstantapp.domain.actions.counters

import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.persistence.DispensaryCounterSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddIngredientToDispensaryCounterAction @Inject constructor(
    private val dispensaryCounterSharedPreference: DispensaryCounterSharedPreference
): BaseAction<AddIngredientToDispensaryCounterAction.Result>() {

    sealed class Result {
        object AddedSuccess: Result()
    }

    fun ingredientAdded() {
        dispensaryCounterSharedPreference
            .ingredientAdded(5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = Result.AddedSuccess
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}