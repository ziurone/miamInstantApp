package com.example.miaminstantapp.domain.usecases

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.domain.repositories.IVolumeUnitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchVolumeUnitsAction @Inject constructor(
    private val userVolumeUnitRepository: IVolumeUnitRepository
):
    IFetchVolumeUnitsAction,
    BaseAction<IFetchVolumeUnitsAction.Result>()
{

    override fun fetch() {
        userVolumeUnitRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IFetchVolumeUnitsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFailureResult(failedResponseCode: String): IFetchVolumeUnitsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess(response: List<VolumeUnitEntity>) {
        liveData.value = IFetchVolumeUnitsAction.Result.Success(response)
        cleanUp()
    }
}