package com.example.mvpapplicaton.presenter.details

import com.example.mvpapplicaton.model.reposetory.details.IUserRepo
import com.example.mvpapplicaton.presenter.navigation.IScreens
import com.example.mvpapplicaton.view.details.UserRepositories
import com.example.mvpapplicaton.view.details.adapter.DetailItemView
import com.example.mvpapplicaton.view.main.adapter.IUserListPresenterDetails
import com.example.mvpapplicaton.view.user.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailsPresenter(
    private val usersRepo: IUserRepo,
    private val url: String
) : MvpPresenter<UsersView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    class UsrListPresenter : IUserListPresenterDetails {

        val userRepo = mutableListOf<UserRepositories>()

        override var itemClickListener: ((DetailItemView) -> Unit)? = null

        override fun bindView(view: DetailItemView) {
            val repo = userRepo[view.pos]
            repo.fullName?.let { view.setRepo(it) }
        }

        override fun getCount() = userRepo.size
    }

    val userRepoPresenter = UsrListPresenter()

    private fun onItemClick(user: UserRepositories) {
        println(user.forks)
        router.navigateTo(screens.navigateToInfo(user.forks ?: 0))
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepo()
        userRepoPresenter.itemClickListener = { it ->
            onItemClick(userRepoPresenter.userRepo[it.pos])

        }
    }

    private fun loadRepo() {
        usersRepo.getUserRep(url)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userRepoPresenter.userRepo.clear()
                userRepoPresenter.userRepo.addAll(it)
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