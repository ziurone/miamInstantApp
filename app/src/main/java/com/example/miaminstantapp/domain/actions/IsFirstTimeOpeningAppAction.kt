package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.persistence.IsFirstTimeInAppPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IsFirstTimeOpeningAppAction @Inject constructor(
    private val isFirstTimeInAppPreference: IsFirstTimeInAppPreference
): BaseAction<IIsFirstTimeOpeningAppAction.Result>(), IIsFirstTimeOpeningAppAction {

    override fun isFirstTimeInApp() {
        isFirstTimeInAppPreference
            .isFirstTime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IIsFirstTimeOpeningAppAction.Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): IIsFirstTimeOpeningAppAction.Result? {
        TODO("Not yet implemented")
    }



    private fun onSuccess(result: Boolean) {
        when (result) {
            false -> {
                liveData.value = IIsFirstTimeOpeningAppAction.Result.UserHasUsedApp
            }
            true -> {
                isFirstTimeInAppPreference
                    .setIsFirstTimeInApp(true)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        liveData.value = IIsFirstTimeOpeningAppAction.Result.UserFirstTimeInApp
                    }, ::onError)
            }
        }
    }
}