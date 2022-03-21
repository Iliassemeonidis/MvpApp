package com.example.mvpapplicaton.di.app

import com.example.mvpapplicaton.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}