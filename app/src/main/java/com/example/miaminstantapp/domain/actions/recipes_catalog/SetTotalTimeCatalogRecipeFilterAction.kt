package com.example.miaminstantapp.domain.actions.recipes_catalog

import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.domain.repositories.ICatalogRecipeTotalTimeMinutesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SetTotalTimeCatalogRecipeFilterAction @Inject constructor(
    private val catalogRecipeTotalTimeMinutesRepository: ICatalogRecipeTotalTimeMinutesRepository
): BaseAction<SetTotalTimeCatalogRecipeFilterAction.Result>() {

    sealed class Result {
        object SetTotalTimeSuccess: Result()
    }

    operator fun invoke(totalMinutes: Int) {
        catalogRecipeTotalTimeMinutesRepository
            .setTotalMinutes(totalMinutes)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = Result.SetTotalTimeSuccess
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}