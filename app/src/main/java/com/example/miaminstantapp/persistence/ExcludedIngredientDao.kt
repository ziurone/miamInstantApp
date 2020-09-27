package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ExcludedIngredientDao {
    @Insert
    fun insert(excludedIngredientEntity: ExcludedIngredientEntity): Completable

    @Query("SELECT * FROM " + ExcludedIngredientEntity.TABLE_NAME)
    fun fetchAll(): Single<List<ExcludedIngredientEntity>>
}