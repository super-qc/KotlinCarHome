package com.study.carhome.di

import android.app.Application
import androidx.room.Room
import com.study.carhome.db.AppDataBase
import com.study.carhome.db.CarBrandDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDataBase(application: Application):AppDataBase{
        return Room.databaseBuilder(application,AppDataBase::class.java,"car_home").build()
    }

    @Singleton
    @Provides
    fun provideCarBrandDao(dataBase: AppDataBase): CarBrandDao{
        return dataBase.carBrandDao()
    }

}