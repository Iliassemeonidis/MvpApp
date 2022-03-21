package com.example.mvpapplicaton.model.reposetory.githubuser

import com.example.mvpapplicaton.model.data.IDataSource
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.model.db.table.RoomGithubUser
import com.example.mvpapplicaton.network.INetworkStatus
import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Single

class RetrofitGithubUsersRepo (
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
    ) : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { user ->
                    Single.fromCallable {
                        val roomUser = user.map { user ->
                            RoomGithubUser(
                                user.id ?: "", user.login ?: "", user.avatarUrl ?: "",
                                user.reposUrl ?: ""
                            )
                        }
                        db.userDao.insert(roomUser)
                        user
                    }
                }
            } else {
                Single.fromCallable {
                    db.userDao.getAll().map { roomUser ->
                        GithubUser(
                            roomUser.id,
                            roomUser.login,
                            roomUser.avatarUrl,
                            roomUser.reposUrl
                        )
                    }
                }
            }
        }
}