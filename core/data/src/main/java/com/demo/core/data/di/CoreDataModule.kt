package com.demo.core.data.di

import com.demo.core.data.networking.HttpClientFactory
import com.demo.core.database.AppDatabase
import com.demo.core.database.dao.UserDao
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import android.app.Application
import androidx.room.Room


val coreDataModule = module{
    single {
        HttpClientFactory().build()
    }
    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single<UserDao> { get<AppDatabase>().userDao() }
}
