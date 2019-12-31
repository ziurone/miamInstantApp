package com.example.miaminstantapp.persistence

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

open class BaseSharedPreferencesManager constructor(
    val sharedPreferences: SharedPreferences
) {

    private val subject = BehaviorSubject.createDefault(sharedPreferences)

    fun Single<SharedPreferences>.editSharedPreferences(batch: SharedPreferences.Editor.() -> Unit): Completable =
        flatMapCompletable {
            Completable.fromAction {
                it.edit().also(batch).apply()
            }
        }

}