package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.repositories.IUserMoneyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SetUserMoneyAction @Inject constructor(
    private val userMoneyRepository: IUserMoneyRepository
): BaseAction<ISetUserMoneyAction.Result>(), ISetUserMoneyAction {

    override fun set(money: Int) {
        userMoneyRepository
            .setUserMoney(money)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): ISetUserMoneyAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): ISetUserMoneyAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess() {
        liveData.value = ISetUserMoneyAction.Result.Success
    }

}