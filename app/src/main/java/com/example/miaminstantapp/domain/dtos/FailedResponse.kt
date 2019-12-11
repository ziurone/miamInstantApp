package com.example.miaminstantapp.domain.dtos

import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber

data class FailedResponse constructor(val code: String?,
                                      val error: String?,
                                      val errors: Any?,
                                      val message: String?) {

    companion object {
        fun build(exception: HttpException): FailedResponse {
            val errorJsonString = exception.response()
                .errorBody()?.string()
            Timber.d("Failed Response:  $errorJsonString")
            return Gson().fromJson(errorJsonString, FailedResponse::class.java)
        }
    }

}