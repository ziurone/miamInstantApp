package com.example.miaminstantapp.domain.actions

import android.util.Log
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.persistence.VolumeUnitDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddVolumeUnitsAction @Inject constructor(
    private val volumeUnitDao: VolumeUnitDao
): IAddVolumeUnitsAction, BaseAction<IAddVolumeUnitsAction.Result>() {

    override fun add(volumeUnits: List<VolumeUnitEntity>) {
        volumeUnitDao
            .insertAll(volumeUnits)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getErrorResult(throwable: Throwable): IAddVolumeUnitsAction.Result? {
        Log.i("ERROR_VOLUME_UNIT", throwable.message)
        return IAddVolumeUnitsAction.Result.Error(throwable.localizedMessage)
    }

    override fun getFailureResult(failedResponseCode: String): IAddVolumeUnitsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess() {
        liveData.value = IAddVolumeUnitsAction.Result.Success
    }
}