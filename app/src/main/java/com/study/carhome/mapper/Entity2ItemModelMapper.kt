package com.study.carhome.mapper

import com.study.carhome.entity.CarBrandEntity
import com.study.carhome.model.CarBrandItemModel

class Entity2ItemModelMapper : Mapper<CarBrandEntity, CarBrandItemModel> {

    override fun map(input: CarBrandEntity): CarBrandItemModel =
        CarBrandItemModel(id = input.id, name = input.name, icon = input.icon)

}