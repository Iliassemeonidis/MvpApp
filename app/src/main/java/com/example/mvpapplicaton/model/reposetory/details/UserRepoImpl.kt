package com.example.mvpapplicaton.model.reposetory.details

import com.example.mvpapplicaton.model.data.IDataSource
import com.example.mvpapplicaton.view.details.UserRepositories
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepoImpl(private val dataSours: IDataSource) : IUserRepo {

    override fun getUserRep(url: String): Single<List<UserRepositories>> =
        dataSours.getUserRep(url).observeOn(Schedulers.io())
}