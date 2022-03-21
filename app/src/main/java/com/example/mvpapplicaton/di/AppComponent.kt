package com.example.mvpapplicaton.di

import com.example.mvpapplicaton.di.api.ApiModule
import com.example.mvpapplicaton.di.app.AppModule
import com.example.mvpapplicaton.di.cache.RetrofitGithubUsersRepoModule
import com.example.mvpapplicaton.di.cicerone.CiceroneModule
import com.example.mvpapplicaton.di.database.DatabaseModule
import com.example.mvpapplicaton.di.datasours.UserRepoModule
import com.example.mvpapplicaton.presenter.details.DetailsPresenter
import com.example.mvpapplicaton.presenter.main.MainPresenter
import com.example.mvpapplicaton.presenter.user.UsersPresenter
import com.example.mvpapplicaton.view.main.MvpActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        RetrofitGithubUsersRepoModule::class,
        CiceroneModule::class,
        UserRepoModule::class
    ]
)
interface AppComponent {
    fun inject(presenter: UsersPresenter)
    fun inject(presenter: DetailsPresenter)
    fun inject(presenter: MainPresenter)
    fun inject(activity: MvpActivity)
}