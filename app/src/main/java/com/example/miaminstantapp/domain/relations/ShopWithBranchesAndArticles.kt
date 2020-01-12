package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.ShopEntity

data class ShopWithBranchesAndArticles(

    @Embedded
    val shop: ShopEntity,

    @Relation(parentColumn = "shopId", entityColumn = "shopId", entity = BranchEntity::class)
    val branchesWithArticles: List<BranchWithArticles>

)