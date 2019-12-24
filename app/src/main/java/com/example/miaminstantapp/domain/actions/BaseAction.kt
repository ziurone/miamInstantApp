package com.example.miaminstantapp.domain.actions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.miaminstantapp.domain.dtos.FailedResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

abstract class BaseAction<T>(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val liveData: MutableLiveData<T> = MutableLiveData()
) : Action<T> {

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun getLiveData(): LiveData<T> = liveData

    override fun cleanUp() {
        compositeDisposable.clear()
    }

    override fun onError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> setLiveDataWithFailure(throwable)
            else -> liveData.value = getErrorResult(throwable)
        }
        cleanUp()
    }

    private fun setLiveDataWithFailure(httpException: HttpException) {
        when (httpException.code()) {
            in 400..510 -> liveData.value = getBasicErrorResult(httpException)
            else -> liveData.value = getErrorResult(httpException)
        }
    }


    abstract fun getErrorResult(throwable: Throwable): T?

    abstract fun getFailureResult(failedResponseCode: String): T?

    private fun getBasicErrorResult(httpException: HttpException): T? {
        val failedResponse = FailedResponse.build(httpException)
        // If the error doesn't come in failedResponse's format,
        // the properties will be null.
        return if (failedResponse.code != null) {
            getFailureResult(failedResponse.code)
        } else {
            getErrorResult(httpException)
        }
    }

}