package com.example.mvpapplicaton.di.cache

import com.example.mvpapplicaton.model.data.IDataSource
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.model.reposetory.githubuser.IGithubUsersRepo
import com.example.mvpapplicaton.model.reposetory.githubuser.RetrofitGithubUsersRepo
import com.example.mvpapplicaton.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RetrofitGithubUsersRepoModule  {

    @Singleton
    @Provides
    fun userRepo(api: IDataSource, networkStatus: INetworkStatus, db: Database): IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, db)

}