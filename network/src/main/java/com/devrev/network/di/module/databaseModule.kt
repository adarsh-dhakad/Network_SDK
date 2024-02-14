package com.devrev.network.di.module

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.devrev.network.room.dp.MoviesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
   fun provideDataBase(application: Application): MoviesDatabase {
       return Room.databaseBuilder(application, MoviesDatabase::class.java,"MoviesDatabase")
           .fallbackToDestructiveMigration()
           .build()
   }
    single { provideDataBase(androidApplication()) }
}