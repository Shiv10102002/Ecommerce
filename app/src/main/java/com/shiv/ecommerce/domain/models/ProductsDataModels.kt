package com.shiv.ecommerce.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductsDataModels(
    var name: String = "",
    var image: String = "",
    var price: String = "",
    var description: String = "",
    var category: String = "",
    var finalPrice: String = "",
    var date: Long = System.currentTimeMillis(),
    var availableUnits:Int = 0,
    var productId:String = "",

)
