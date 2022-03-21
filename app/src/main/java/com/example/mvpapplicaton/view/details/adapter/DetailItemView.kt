package com.example.mvpapplicaton.view.details.adapter

import com.example.mvpapplicaton.view.main.adapter.IItemView

interface DetailItemView : IItemView {
    fun setRepo(repo: String)
}