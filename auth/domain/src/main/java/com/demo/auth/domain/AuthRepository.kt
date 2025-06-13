package com.demo.auth.domain

import com.demo.core.domain.util.DataError
import com.demo.core.domain.util.EmptyDataResult


interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyDataResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network>
}