package com.example.miaminstantapp.domain.actions

import android.util.Log
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import com.example.miaminstantapp.domain.repositories.IMarketIngredientRepository
import com.example.miaminstantapp.domain.repositories.MarketIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoveMarketIngredientAction @Inject constructor(
    private val marketIngredientRepository: IMarketIngredientRepository
): BaseAction<RemoveMarketIngredientAction.Result>() {

    sealed class Result {
        object Success: Result()
        object Error: Result()
    }

    fun remove(marketIngredient: MarketIngredientEntity) {
        marketIngredientRepository
            .delete(marketIngredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onResult, ::onError)
            .track()
    }

    private fun onResult() {
        liveData.value = Result.Success
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        Log.i("Error", "ERror")
        return Result.Error
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }

}