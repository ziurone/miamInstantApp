package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserAddressEntity
import com.example.miaminstantapp.persistence.UserAddressDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchCurrentUserAddressAction @Inject constructor(
    val userAddressDao: UserAddressDao
): BaseAction<IFetchCurrentUserAddressAction.Result>(), IFetchCurrentUserAddressAction {

    override fun fetch() {
        userAddressDao.fetch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess(address: UserAddressEntity?){
        liveData.value = IFetchCurrentUserAddressAction.Result.Success(address)
    }

    override fun getErrorResult(throwable: Throwable): IFetchCurrentUserAddressAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchCurrentUserAddressAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}