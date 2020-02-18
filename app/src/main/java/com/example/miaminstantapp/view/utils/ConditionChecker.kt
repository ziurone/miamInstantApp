package com.example.miaminstantapp.view.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class ConditionChecker<T : ConditionChecker.Condition<T>> {

    private val conditions: MutableList<T> = mutableListOf()
    private var disposable: Disposable? = null

    override fun toString(): String {
        var conditionMessage = "${javaClass.simpleName} "
        conditions.forEach {
            conditionMessage = conditionMessage + "${it.getValue()} "
        }
        return conditionMessage
    }

    fun start() {
        stop()
        val observables = arrayListOf<PublishSubject<Boolean>>()
        conditions.forEach {
            observables.add(it.publishSubject)
        }
        disposable = Observable.combineLatest(observables, checkUpdates())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(updateStatus())
    }

    fun createCondition(): T {
        val condition = getConditionInstance()
        conditions.add(condition)
        return condition
    }

    fun stop() {
        disposable?.dispose()
    }

    private fun checkUpdates(): (Array<Any>) -> Boolean {
        return {
            var result: Boolean = true
            it.forEach {
                val value = it as Boolean
                result = result && value
            }
            result
        }
    }

    abstract fun updateStatus(): (Boolean) -> Unit
    abstract fun getConditionInstance(): T

    abstract class Condition<T> {
        private var value: Boolean = false
            set(value) {
                field = value
                publishSubject.onNext(value)
            }

        val publishSubject = PublishSubject.create<Boolean>()

        fun getValue() : Boolean = value

        fun update(newState: Boolean) {
            value = newState
        }

    }

}