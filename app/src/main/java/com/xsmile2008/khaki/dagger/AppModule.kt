package com.xsmile2008.khaki.dagger

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.xsmile2008.khaki.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vladstarikov on 10/21/17.
 */

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, "app_database").allowMainThreadQueries().build()
}