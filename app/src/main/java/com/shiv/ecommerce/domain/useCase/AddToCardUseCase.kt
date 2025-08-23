package com.shiv.ecommerce.domain.useCase

import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.models.CartDataModels
import com.shiv.ecommerce.domain.models.CategoryDataModels
import com.shiv.ecommerce.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToCardUseCase @Inject constructor(private val repo: Repo){

    fun addToCart(cartDataModels: CartDataModels):Flow<ResultState<String>>{
        return repo.addToCart(cartDataModels)
    }
}