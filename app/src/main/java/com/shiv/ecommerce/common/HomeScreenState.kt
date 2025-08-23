package com.shiv.ecommerce.common

import com.shiv.ecommerce.domain.models.BannerdataModels
import com.shiv.ecommerce.domain.models.CategoryDataModels
import com.shiv.ecommerce.domain.models.ProductsDataModels

sealed class HomeScreenState(
    val isLoading: Boolean = true,
    val errorMessage:String? = null,
    val categories:List<CategoryDataModels>? = null,
    val products:List<ProductsDataModels>? = null,
    val banner:List<BannerdataModels>? = null

)