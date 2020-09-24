package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.domain.repositories.IDietRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoveDietAction @Inject constructor(
    private val dietRepository: IDietRepository
): BaseAction<RemoveDietAction.Result>() {

    sealed class Result {
        object Success: Result()
    }

    fun removeDiet(diet: Diet) {
        dietRepository
            .removeDiet(diet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onRemoveSuccess, ::onError)
            .track()
    }

    private fun onRemoveSuccess() {
        liveData.value = Result.Success
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}