package com.example.mvpapplicaton.presenter.navigation

import com.example.mvpapplicaton.view.user.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun navigateTo(data: GithubUser):Screen
    fun navigateToInfo(fork: Int) : Screen
}