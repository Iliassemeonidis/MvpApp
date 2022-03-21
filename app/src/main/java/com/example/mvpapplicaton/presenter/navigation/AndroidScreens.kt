package com.example.mvpapplicaton.presenter.navigation

import com.example.mvpapplicaton.view.details.DetailsFragment
import com.example.mvpapplicaton.view.info.InfoFragment
import com.example.mvpapplicaton.view.user.GithubUser
import com.example.mvpapplicaton.view.user.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun navigateTo(data: GithubUser) = FragmentScreen { DetailsFragment.newInstance(data) }
    override fun navigateToInfo(fork: Int) = FragmentScreen { InfoFragment.newInstance(fork) }
}