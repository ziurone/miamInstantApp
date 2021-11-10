package com.example.miaminstantapp.domain.actions

import android.util.Log
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.domain.repositories.IVolumeUnitRepository
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitDto
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitsListResponse
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
        Log.i("NETWORK_ERROR", throwable.localizedMessage)
        return IFetchVolumeUnitsAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IFetchVolumeUnitsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess(response: VolumeUnitsListResponse) {
        liveData.value = IFetchVolumeUnitsAction.Result.Success(response.volumeUnits.map {
            Log.i("volumeUnit", it.name)
            it.toEntity()
        })
        cleanUp()
    }
}