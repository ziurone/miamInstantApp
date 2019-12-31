package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.BranchEntity

data class BranchesListResponse(
    val branches: List<BranchEntity>
)