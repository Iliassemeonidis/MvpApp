package com.example.mvpapplicaton.model.reposetory.details

import com.example.mvpapplicaton.view.details.UserRepositories
import io.reactivex.rxjava3.core.Single

interface IUserRepo {
    fun getUserRep(url: String):Single<List<UserRepositories>>
}