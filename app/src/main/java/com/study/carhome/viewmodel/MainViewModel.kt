package com.study.carhome.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.study.carhome.model.CarBrandItemModel
import com.study.carhome.repository.Repository

class MainViewModel @ViewModelInject constructor(
    private val carBrandRepository: Repository
) : ViewModel() {
    val data: LiveData<PagingData<CarBrandItemModel>> =
        carBrandRepository.fetchCarBrandList().cachedIn(viewModelScope).asLiveData()
}