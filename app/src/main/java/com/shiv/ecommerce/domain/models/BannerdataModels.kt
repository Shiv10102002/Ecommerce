package com.shiv.ecommerce.domain.models

data class BannerdataModels(
    var name: String="",
    var image:String="",
    var date: Long = System.currentTimeMillis()
)
