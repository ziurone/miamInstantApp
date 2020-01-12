package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.ShopArticleEntity

data class BranchWithArticles(

    @Embedded
    val branch: BranchEntity,

    @Relation(parentColumn = "branchId", entityColumn = "branchId")
    val articles: List<ShopArticleEntity>
)