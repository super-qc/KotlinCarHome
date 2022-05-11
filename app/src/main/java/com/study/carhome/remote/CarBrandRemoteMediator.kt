package com.study.carhome.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.study.carhome.db.AppDataBase
import com.study.carhome.entity.CarBrandEntity

@OptIn(ExperimentalPagingApi::class)
class CarBrandRemoteMediator(
    private val api: CarBrandService,
    private val database: AppDataBase
) : RemoteMediator<Int, CarBrandEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CarBrandEntity>
    ): MediatorResult {
        // 1.判断loadType
        // 2.请求网络分页数据

        // 3.插入数据库
        TODO()
    }

}