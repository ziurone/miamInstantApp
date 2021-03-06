package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CatalogRecipeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val content: String,
    val preparingMinutes: Int,
    val cookingMinutes: Int,
    val totalMinutes: Int,
    val imageUrl: String?,
){
    companion object {
        const val TABLE_NAME = "catalogRecipes"
    }
}