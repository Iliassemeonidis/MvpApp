package com.example.mvpapplicaton.di.datasours

import com.example.mvpapplicaton.model.data.IDataSource
import com.example.mvpapplicaton.model.reposetory.details.IUserRepo
import com.example.mvpapplicaton.model.reposetory.details.UserRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserRepoModule {

    @Singleton
    @Provides
     fun getUserRep(iDataSource: IDataSource): IUserRepo = UserRepoImpl(iDataSource)
}