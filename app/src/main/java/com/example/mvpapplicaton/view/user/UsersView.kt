package com.example.mvpapplicaton.view.user

import com.example.mvpapplicaton.view.main.MainView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MainView {
    fun init()
    fun updateList()
}