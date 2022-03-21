package com.example.mvpapplicaton.presenter.user

import com.example.mvpapplicaton.model.reposetory.githubuser.IGithubUsersRepo
import com.example.mvpapplicaton.presenter.navigation.IScreens
import com.example.mvpapplicaton.view.details.UserRepositories
import com.example.mvpapplicaton.view.main.adapter.IUserListPresenter
import com.example.mvpapplicaton.view.main.adapter.UserItemView
import com.example.mvpapplicaton.view.user.GithubUser
import com.example.mvpapplicaton.view.user.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter(
    private val uiScheduler: Scheduler,
//    private val usersRepo: IGithubUsersRepo,
//    private val router: Router,
//    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    private fun onItemClick(user: GithubUser) {
        router.navigateTo(screens.navigateTo(user))
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { it ->
            onItemClick(usersListPresenter.users[it.pos])
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
