package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserAddressEntity
import com.example.miaminstantapp.persistence.UserAddressDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddUserAddressAction @Inject constructor(
    val userAddressDao: UserAddressDao
): BaseAction<IAddUserAddressAction.Result>(), IAddUserAddressAction {

    override fun add(address: UserAddressEntity) {
        userAddressDao.insert(address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = IAddUserAddressAction.Result.Success
    }

    override fun getErrorResult(throwable: Throwable): IAddUserAddressAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IAddUserAddressAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}