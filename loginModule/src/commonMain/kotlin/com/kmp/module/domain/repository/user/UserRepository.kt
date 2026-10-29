package com.kmp.module.domain.repository.user

import com.kmp.module.domain.model.login.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user : UserModel) : Flow<Unit>
    
    suspend fun saveUserToken(token : String) : Flow<Unit>
}