package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.persistence.CatalogRecipeTotalTimeFilterPreference
import com.example.miaminstantapp.persistence.UserMoneySharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CatalogRecipeTotalTimeMinutesRepository @Inject constructor(
    private val catalogRecipeTotalTimeFilterPreference: CatalogRecipeTotalTimeFilterPreference
) : ICatalogRecipeTotalTimeMinutesRepository {

    override fun setTotalMinutes(totalMinutes: Int): Completable {
        return catalogRecipeTotalTimeFilterPreference
            .setTotalTimeFilter(totalMinutes)
    }

    override fun getTotalMinutes(): Single<Int> = catalogRecipeTotalTimeFilterPreference.getTotalTimeFilter()

}