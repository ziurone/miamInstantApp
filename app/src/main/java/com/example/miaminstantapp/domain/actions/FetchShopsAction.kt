package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.repositories.IBranchRepository
import com.example.miaminstantapp.domain.repositories.IShopRepository
import com.example.miaminstantapp.persistence.BranchDao
import com.example.miaminstantapp.persistence.ShopDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchShopsAction @Inject constructor(
    private val branchRepository: IBranchRepository,
    private val branchDao: BranchDao,
    private val shopRepository: IShopRepository,
    private val shopDao: ShopDao
):
    BaseAction< IFetchShopsAction.Result>(),
    IFetchShopsAction
{
    override fun fetchShopsAndBranches(lat: String, long: String, squares: Int) {
        branchRepository
            .fetchBranches(lat, long, squares)
            .flatMapCompletable { branches ->
                branchDao.insertAll(branches)
            }
            .andThen(shopRepository.fetchShops(listOf(1,2,3)))
            .flatMapCompletable { shops ->
                shopDao.insertAll(shops)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    override fun getFailureResult(failedResponseCode: String): IFetchShopsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorResult(throwable: Throwable): IFetchShopsAction.Result? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onSuccess() {
        liveData.value = IFetchShopsAction.Result.Success
    }
}