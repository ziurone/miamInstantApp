package com.example.miaminstantapp.domain.actions.recipes_catalog

import com.example.miaminstantapp.domain.actions.BaseAction

class SetTotalTimeCatalogRecipeFilterAction: BaseAction<SetTotalTimeCatalogRecipeFilterAction.Result>() {

    sealed class Result {
        object SetTotalTimeSuccess: Result()
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}