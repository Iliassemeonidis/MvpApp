package com.example.mvpapplicaton.network

import io.reactivex.rxjava3.core.Single

interface INetworkStatus {
    fun isOnlineSingle(): Single<Boolean>
}