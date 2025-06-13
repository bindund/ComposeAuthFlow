package com.demo.auth.data

import com.demo.auth.domain.AuthRepository
import com.demo.core.domain.util.DataError
import com.demo.core.domain.util.EmptyDataResult
import io.ktor.client.HttpClient
import com.demo.core.data.networking.post
import com.demo.core.database.dao.UserDao
import com.demo.core.database.entity.UserEntity
import com.demo.core.domain.util.AppResult
import com.demo.core.domain.util.asEmptyDataResult
import timber.log.Timber

//import com.demo.core.data.HTTPC


class AuthRepositoryImpl(
    private val userDao: UserDao
): AuthRepository {

    override suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network> {
        val existing = userDao.getUserByEmail(email)
        return if (existing != null) {
            Timber.d("User already exists with email: $email")
            AppResult.Error(DataError.Network.CONFLICT)
        } else {
//            userDao.insertUser(UserEntity(id = 0, email = email, password = password))
//            val newUser = UserEntity(id = 0, email = email, password = password)
            val newUser = UserEntity(email = email, password = password) // No need to pass id

            userDao.insertUser(newUser)
            Timber.d("Inserted User: email=${newUser.email}, password=${newUser.password}")

            AppResult.Success(Unit)
        }.asEmptyDataResult()
    }

    override suspend fun login(email: String, password: String): EmptyDataResult<DataError.Network> {
        val user = userDao.getUserByEmail(email)
        return if (user?.password == password) {
            AppResult.Success(Unit)
        } else {
            AppResult.Error(DataError.Network.UNAUTHORIZED)
        }.asEmptyDataResult()
    }

}