package com.shiv.ecommerce.domain.models

data class CategoryDataModels(
    var name: String = "",
    var image: String = "",
    var date: Long = System.currentTimeMillis(),
    var createBy:String = "",
)
