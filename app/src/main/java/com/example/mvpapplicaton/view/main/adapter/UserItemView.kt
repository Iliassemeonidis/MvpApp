package com.example.mvpapplicaton.view.main.adapter

interface UserItemView : IItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String)
}