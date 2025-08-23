package com.shiv.ecommerce.domain.useCase

import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.models.ProductsDataModels
import com.shiv.ecommerce.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(private val repo: Repo) {
    fun getAllProduct(): Flow<ResultState<List<ProductsDataModels>>> {
        return repo.getAllProducts()
    }
}