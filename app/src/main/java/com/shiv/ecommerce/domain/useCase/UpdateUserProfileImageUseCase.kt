package com.shiv.ecommerce.domain.useCase

import android.net.Uri
import com.shiv.ecommerce.common.ResultState
import com.shiv.ecommerce.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserProfileImageUseCase @Inject constructor(private val repo: Repo) {

    fun updateUserProfileImage(uri: Uri): Flow<ResultState<String>> {
        return repo.updateUserProfileImage(uri)
    }
}