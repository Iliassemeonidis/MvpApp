package com.example.mvpapplicaton.view.main.adapter

import com.example.mvpapplicaton.view.details.adapter.DetailItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
interface IUserListPresenterDetails : IListPresenter<DetailItemView>