package com.shiv.ecommerce.domain.useCase

import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.models.UserData
import com.shiv.ecommerce.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repo: Repo) {
    fun createUser(userData: UserData): Flow<ResultState<String>> {
       return repo.registerUserWithEmailAndPassword(userData)
    }
}