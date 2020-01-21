package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import com.example.miaminstantapp.domain.entities.ShopEntity

data class ShopPurchaseRelation (

    @Embedded
    val shop: ShopEntity,

    val purchasePrice: Float

)