package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.persistence.UserAddressDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoveAddressAction @Inject constructor(
    private val addressDao: UserAddressDao
): BaseAction<RemoveAddressAction.Result>() {

    sealed class Result {
        object Success: Result()
    }

    fun remove() {
        addressDao
            .removeAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = Result.Success
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }

}