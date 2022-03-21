package com.example.mvpapplicaton

import android.app.Application
import com.example.mvpapplicaton.di.AppComponent
import com.example.mvpapplicaton.di.DaggerAppComponent
import com.example.mvpapplicaton.di.app.AppModule
import com.example.mvpapplicaton.model.db.Database

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}