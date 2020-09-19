package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.domain.repositories.DietRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DietAction @Inject constructor(
    private val dietRepository: DietRepository
): BaseAction<DietAction.Result>() {

    sealed class Result {
        object SUCCESS: Result()
    }

    fun addDiet(diet: Diet) {
        dietRepository
            .addDiet(diet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")}

    fun onSuccess() {}

}