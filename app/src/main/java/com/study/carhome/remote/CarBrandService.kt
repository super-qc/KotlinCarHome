package com.study.carhome.remote

import com.study.carhome.model.CarBrandItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CarBrandService {

    @GET("carBrand.php")
    suspend fun fetchCarBrandList(
        @Query("since") since: Int,
        @Query("pagesize") pageSize: Int
    ): List<CarBrandItemModel>
}