package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.persistence.UserIngredientDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoveUserIngredientAction @Inject constructor(
    private val userIngredientDao: UserIngredientDao
): BaseAction<RemoveUserIngredientAction.Result>() {
    sealed class Result {
        object Success: Result()
    }

    fun delete(ingredient: UserIngredientEntity) {
        userIngredientDao
            .delete(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    fun onSuccess() {
        this.liveData.value = Result.Success
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}