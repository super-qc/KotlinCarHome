package com.study.carhome.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.study.carhome.db.AppDataBase
import com.study.carhome.entity.CarBrandEntity
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class CarBrandRemoteMediator(
    private val api: CarBrandService,
    private val database: AppDataBase
) : RemoteMediator<Int, CarBrandEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CarBrandEntity>
    ): MediatorResult {

        try {
            // 1.判断loadType,根据loadType计算page
            Log.d("CarHome", "loadType:$loadType")
            val pageKey = when (loadType) {
                // 首次访问，或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> null
                // 在当前加载的数据集的头部添加数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                // 加载更多
                LoadType.APPEND -> {
                    val lastItem: CarBrandEntity =
                        state.lastItemOrNull() ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.page

                }
            }


            // 2.请求网络分页数据
            val page = pageKey ?: 0
            val result = api.fetchCarBrandList(
                page * state.config.pageSize,
                state.config.pageSize
            )
            val item = result.map {
                CarBrandEntity(
                    id=it.id,
                    name = it.name,
                    icon=it.icon,
                    page = page+1
                )
            }

            // 3.插入数据库
            val endOfPaginationReached = result.isEmpty()
            val carBrandDao=database.carBrandDao()
            database.withTransaction {
                if(loadType==LoadType.REFRESH){
                    carBrandDao.clearCarBrand()
                }
                carBrandDao.insertCarBrand(item)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            e.printStackTrace()

        }

        TODO()
    }

}