package com.example.mvpapplicaton.model.data

import com.example.mvpapplicaton.model.db.table.RoomGithubRepository
import com.example.mvpapplicaton.view.details.UserRepositories
import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRep(@Url url: String): Single<List<UserRepositories>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<GithubUser>

    @GET
    fun getRepositories(url: String): Single<List<RoomGithubRepository>>
}