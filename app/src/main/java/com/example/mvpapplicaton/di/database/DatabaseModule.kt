package com.example.mvpapplicaton.di.database

import androidx.room.Room
import com.example.mvpapplicaton.App
import com.example.mvpapplicaton.model.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

//    private var instance: Database? = null

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

//    @Singleton
//    @Provides
//    fun getInstance() = instance
//        ?: throw RuntimeException("Database has not been created. Please call create(context)")
}