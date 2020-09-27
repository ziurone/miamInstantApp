package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.domain.repositories.IExcludedIngredientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchExcludedIngredientsAction @Inject constructor(
    private val excludedIngredientRepository: IExcludedIngredientRepository
): BaseAction<FetchExcludedIngredientsAction.Result>() {
  sealed class Result {
      data class Success(val ingredients: List<ExcludedIngredientEntity>): Result()
  }
    fun fetch() {
        excludedIngredientRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    fun onSuccess(ingredients: List<ExcludedIngredientEntity>) {
        liveData.value = Result.Success(ingredients)
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}