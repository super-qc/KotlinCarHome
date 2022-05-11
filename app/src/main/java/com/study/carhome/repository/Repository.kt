package com.study.carhome.repository

import androidx.paging.PagingData
import com.study.carhome.model.CarBrandItemModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchCarBrandList(): Flow<PagingData<CarBrandItemModel>>
}