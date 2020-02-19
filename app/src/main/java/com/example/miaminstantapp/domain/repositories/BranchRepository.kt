package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.persistence.BranchDao
import io.reactivex.Single
import javax.inject.Inject

class BranchRepository @Inject constructor(
    private val branchDao: BranchDao
) : IBranchRepository {
    override fun fetchBranches(lat: String, long: String, squares: Int): Single<List<BranchEntity>> {
        return Single.just(
            listOf(
                BranchEntity(1, "Coto Boedo", "34.100000", "32.100000", "Boedo 900", 1),
                BranchEntity(2, "Coto Boedo 2", "34.100000", "32.100000", "Boedo 1000", 2)
            )
        )
    }

    override fun fetchAllFromLocal(): Single<List<BranchEntity>> = branchDao.fetchAll()
}