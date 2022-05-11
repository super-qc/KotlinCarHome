package com.study.carhome.entity

import androidx.room.Entity

@Entity
data class CarBrandEntity(
    val id: Int,
    val name: String,
    val page: Int = 0,
    val icon: String
)