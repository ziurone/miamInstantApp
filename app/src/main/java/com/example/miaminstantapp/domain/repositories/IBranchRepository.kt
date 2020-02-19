package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.BranchEntity
import io.reactivex.Single

interface IBranchRepository {
    fun fetchBranches(lat: String, long: String, squares: Int): Single<List<BranchEntity>>
    fun fetchAllFromLocal(): Single<List<BranchEntity>>
}