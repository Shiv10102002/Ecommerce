package com.shiv.ecommerce.domain.useCase

import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repo: Repo) {
    fun loginUserWithEmailAndPassword(email: String, password: String): Flow<ResultState<String>> {
        return repo.loginUserWithEmailAndPassword(email, password)
    }

}