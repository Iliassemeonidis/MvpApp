package com.example.mvpapplicaton.model.reposetory.githubuser

import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}