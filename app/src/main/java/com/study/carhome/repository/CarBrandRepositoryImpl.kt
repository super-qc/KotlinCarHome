package com.study.carhome.repository

import androidx.paging.*
import coil.request.Disposable
import com.study.carhome.db.AppDataBase
import com.study.carhome.entity.CarBrandEntity
import com.study.carhome.mapper.Mapper
import com.study.carhome.model.CarBrandItemModel
import com.study.carhome.remote.CarBrandRemoteMediator
import com.study.carhome.remote.CarBrandService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.Dispatcher

class CarBrandRepositoryImpl(
    private val api: CarBrandService,
    private val database: AppDataBase,
    private val mapper2ItemModel: Mapper<CarBrandEntity, CarBrandItemModel>

) : Repository {
    override fun fetchCarBrandList(): Flow<PagingData<CarBrandItemModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 8,
                prefetchDistance = 1,
                initialLoadSize = 16
            ),
            remoteMediator = CarBrandRemoteMediator(api, database)
        ) {
            database.carBrandDao().getCarBrand()
        }.flow.flowOn(Dispatchers.IO).map { pagingData ->
            pagingData.map { mapper2ItemModel.map(it) }
        }
    }

}