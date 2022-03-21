package com.example.mvpapplicaton.model.reposetory.githubuser

import com.example.mvpapplicaton.model.data.IDataSource
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.model.db.table.RoomGithubRepository
import com.example.mvpapplicaton.network.INetworkStatus
import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource, private val networkStatus:
    INetworkStatus, private val db: Database
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<Any>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getRepositories(url)
                    .flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = user.login?.let {
                                db.userDao.findByLogin(it)
                            } ?: throw RuntimeException("No such user in cache")
                            val roomRepos = repositories.map {
                                RoomGithubRepository(
                                    it.id ?: "", it.name ?: "", it.forksCount ?: 0,
                                    roomUser.id
                                )
                            }
                            db.repositoryDao.insert(roomRepos)
                            repositories
                        }
                    }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(
                Schedulers.io()
            )
        } else {
            Single.fromCallable {
                val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                    ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id).map {
                    GithubRepository(it.id, it.name, it.forksCount)
                }
            }
        }
    }.subscribeOn(Schedulers.io())

}