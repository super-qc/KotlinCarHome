package com.study.carhome.di

import com.study.carhome.db.AppDataBase
import com.study.carhome.mapper.Entity2ItemModelMapper
import com.study.carhome.remote.CarBrandService
import com.study.carhome.repository.CarBrandRepositoryImpl
import com.study.carhome.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @ActivityScoped
    @Provides
    fun provideCarBrandRepository(
        api: CarBrandService, database: AppDataBase): Repository {
        return CarBrandRepositoryImpl(api,database,Entity2ItemModelMapper())
    }

}