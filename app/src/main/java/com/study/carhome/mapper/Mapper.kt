package com.study.carhome.mapper

interface Mapper<I, O> {
    fun map(input : I): O
}