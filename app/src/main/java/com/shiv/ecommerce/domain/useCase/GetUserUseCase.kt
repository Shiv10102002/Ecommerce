package com.shiv.ecommerce.domain.useCase

import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.models.UserDataParent
import com.shiv.ecommerce.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo : Repo) {
    fun getUserById(userId:String): Flow<ResultState<UserDataParent>> {
        return repo.getUserById(userId)
    }
}