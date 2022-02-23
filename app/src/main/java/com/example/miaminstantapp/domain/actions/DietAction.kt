package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.DietEntity
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.domain.repositories.DietRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DietAction @Inject constructor(
    private val dietRepository: DietRepository
): BaseAction<DietAction.Result>() {

    sealed class Result {
        data class FetchUserDietsSuccess(val diets: List<DietEntity>): Result()
        object AddUserDietSuccess: Result()
        object AddUserDietsSuccess: Result()
    }

    fun getDiets() = dietRepository.getDiets()

    fun getUserDiets() {
        dietRepository
            .getUserDiets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onFetchAllSuccess, ::onError)
            .track()
    }

    private fun onFetchAllSuccess(diets: List<DietEntity>) {
        liveData.value = Result.FetchUserDietsSuccess(diets)
    }

    fun addDiet(diet: Diet) {
        dietRepository
            .addDiet(diet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onAddSuccess, ::onError)
            .track()
    }

    fun addDiets(diets: List<Diet>) {
        dietRepository
            .addDiets(diets)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onAddAllSuccess, ::onError)
            .track()
    }

    private fun onAddAllSuccess() {
        liveData.value = Result.AddUserDietsSuccess
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")}

    private fun onAddSuccess() {
        liveData.value = Result.AddUserDietSuccess
    }

}